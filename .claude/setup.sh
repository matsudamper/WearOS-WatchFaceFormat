#!/usr/bin/env bash
set -euo pipefail

CMDLINE_TOOLS_REVISION="16111833"
# AGP の DEFAULT_BUILD_TOOLS_REVISION。compileSdk とは独立していて、
# 揃えないと AGP がビルド中に別バージョンを取りに行く
BUILD_TOOLS_VERSION="36.0.0"
# compileSdk と別バージョンの platform を入れても AGP が SDK 未検出で落ちるので、ビルドファイルを正とする
COMPILE_SDK_FILE="build-logic/libs.versions.toml"

if [ -n "${CLAUDE_PROJECT_DIR:-}" ] && [ "${CLAUDE_CODE_REMOTE:-}" != "true" ]; then
  exit 0
fi

# セットアップスクリプト欄から呼ぶときは cwd がリポジトリ外なので、自分の位置から解決する
cd "${CLAUDE_PROJECT_DIR:-$(cd "$(dirname "$0")/.." && pwd)}"

# 構築済みの SDK を無視して入れ直さないよう、local.properties の sdk.dir も候補にする
sdk_dir_in_local_properties=""
if [ -f local.properties ]; then
  sdk_dir_in_local_properties="$(sed -n 's/^[[:space:]]*sdk\.dir[[:space:]]*=[[:space:]]*\(.*\)$/\1/p' local.properties | tail -n 1)"
fi
ANDROID_SDK_ROOT="${ANDROID_SDK_ROOT:-${ANDROID_HOME:-${sdk_dir_in_local_properties:-${HOME}/android-sdk}}}"
ANDROID_CLI="${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin/android"

compile_sdk_major="$(sed -n -E \
  -e 's/^[[:space:]]*(androidCompileSdk|compileSdk)[[:space:]]*=[[:space:]]*"?([0-9]+)"?[[:space:]]*$/\2/p' \
  -e 's/.*compileSdkVersion\("android-([0-9]+)\.[0-9]+"\).*/\1/p' \
  "${COMPILE_SDK_FILE}" | head -n 1)"
compile_sdk_minor="$(sed -n -E \
  -e 's/^[[:space:]]*(androidCompileSdkMinor|compileSdkMinor)[[:space:]]*=[[:space:]]*"?([0-9]+)"?[[:space:]]*$/\2/p' \
  -e 's/.*compileSdkVersion\("android-[0-9]+\.([0-9]+)"\).*/\1/p' \
  "${COMPILE_SDK_FILE}" | head -n 1)"
if [ -z "${compile_sdk_major}" ]; then
  echo "${COMPILE_SDK_FILE} から compileSdk を読めなかった" >&2
  exit 1
fi
compile_sdk_minor="${compile_sdk_minor:-0}"
# SDK のパッケージ名は API 36 までは android-36 / android-36.1、API 37 からは android-37.0 と .0 も付く
if [ "${compile_sdk_major}" -le 36 ] && [ "${compile_sdk_minor}" = 0 ]; then
  platform="android-${compile_sdk_major}"
else
  platform="android-${compile_sdk_major}.${compile_sdk_minor}"
fi

# SessionStart フックからも呼ぶので、揃っているときは android sdk install を起動しない。
# 何もすることが無くても 1.7 秒ほどかかる
if [ ! -f "${ANDROID_SDK_ROOT}/platforms/${platform}/android.jar" ] ||
  [ ! -d "${ANDROID_SDK_ROOT}/build-tools/${BUILD_TOOLS_VERSION}" ] ||
  [ ! -x "${ANDROID_SDK_ROOT}/platform-tools/adb" ] ||
  [ ! -x "${ANDROID_CLI}" ]; then
  # これまで入れていた 13114758 の cmdline-tools には Android CLI(android コマンド)が無いので、android の有無で入れ替える
  if [ ! -x "${ANDROID_CLI}" ]; then
    echo "[setup] cmdline-tools を導入する"
    mkdir -p "${ANDROID_SDK_ROOT}/cmdline-tools"
    tmp="$(mktemp -d)"
    trap 'rm -rf "${tmp}"' EXIT
    # プロキシ経由だと転送が途中で切れて壊れた zip になることがあるので、確かめて取り直す
    for attempt in 1 2 3 4; do
      if curl -fsSL -o "${tmp}/cmdline-tools.zip" "https://dl.google.com/android/repository/commandlinetools-linux-${CMDLINE_TOOLS_REVISION}_latest.zip" &&
        unzip -tq "${tmp}/cmdline-tools.zip" > /dev/null; then
        break
      fi
      if [ "${attempt}" = 4 ]; then
        echo "cmdline-tools を取得できなかった" >&2
        exit 1
      fi
    done
    unzip -q "${tmp}/cmdline-tools.zip" -d "${tmp}"
    rm -rf "${ANDROID_SDK_ROOT}/cmdline-tools/latest"
    mv "${tmp}/cmdline-tools" "${ANDROID_SDK_ROOT}/cmdline-tools/latest"
  fi

  echo "[setup] Android SDK パッケージを導入する (${platform})"
  # ライセンスは android sdk install が承諾して licenses/ に書くので、別の手順は要らない
  "${ANDROID_CLI}" --sdk="${ANDROID_SDK_ROOT}" sdk install \
    "platform-tools" \
    "platforms/${platform}" \
    "build-tools/${BUILD_TOOLS_VERSION}" < /dev/null > /dev/null
fi

# 環境変数(ANDROID_HOME)はセットアップ後のシェルに残らないので local.properties に書く。
# cmake.dir など他のローカル設定を消さないよう sdk.dir の行だけ差し替える
tmp_local_properties="$(mktemp ./.local.properties.XXXXXX)"
if [ -f local.properties ]; then
  grep -v -E '^[[:space:]]*sdk\.dir[[:space:]]*=' local.properties > "${tmp_local_properties}" || true
fi
echo "sdk.dir=${ANDROID_SDK_ROOT}" >> "${tmp_local_properties}"
mv "${tmp_local_properties}" local.properties

# hook の環境変数は CLAUDE_ENV_FILE に書いたものだけがセッションに残る
if [ -n "${CLAUDE_ENV_FILE:-}" ]; then
  path_export="export PATH=$(printf '%q' "${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin"):\$PATH"
  grep -qxF "${path_export}" "${CLAUDE_ENV_FILE}" 2>/dev/null || echo "${path_export}" >> "${CLAUDE_ENV_FILE}"
fi

android_user_home="${ANDROID_USER_HOME:-${HOME}/.android}"
if [ ! -f "${android_user_home}/debug.keystore" ]; then
  echo "[setup] debug.keystore を作る"
  mkdir -p "${android_user_home}"
  keytool -genkeypair -keystore "${android_user_home}/debug.keystore" \
    -storepass android -alias androiddebugkey -keypass android \
    -keyalg RSA -keysize 2048 -validity 10000 \
    -dname "CN=Android Debug,O=Android,C=US" > /dev/null
fi

echo "[setup] 完了"
