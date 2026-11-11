#!/usr/bin/env bash
# android-emulator-runner の script は1行ずつ別シェルで実行されるため、一連の処理をこのスクリプトにまとめる
set -euxo pipefail

adb wait-for-device
adb install -r app/build/outputs/apk/debug/app-debug.apk

# プレビューの見た目を毎回揃えるため時刻を 10:10 に固定する。root が取れないイメージでは固定せずに続行する
adb shell settings put global auto_time 0 || true
if adb root; then
  adb wait-for-device
  adb shell date 0101101026.00 || true
fi

adb shell am broadcast \
  -a com.google.android.wearable.app.DEBUG_SURFACE \
  --es operation set-watchface \
  --es watchFaceId "$APPLICATION_ID"

# 画面を点灯させてアンビエントモードを解除する
adb shell input keyevent KEYCODE_WAKEUP
sleep 15
adb exec-out screencap -p > preview_raw.png
