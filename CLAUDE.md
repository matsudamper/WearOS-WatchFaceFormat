# CLAUDE.md

## プロジェクト概要
WearOS向けウォッチフェイスアプリ。DSLを使ってWatch Face Format形式のXMLを生成する。

## ビルド
```shell
./gradlew assembleDebug
./gradlew assembleRelease
```

## プロジェクト構成
- `app/` - Android アプリモジュール
- `build-logic/` - Watch Face Format XML生成用のGradleプラグインとDSL

## 言語・技術
- Kotlin
- Gradle (Kotlin DSL)
- Android Gradle Plugin
- Watch Face Format (WFF)
