# My Watchface
<img width="400" src="README/face.png">

# 仕組み
DSLを通して[Watch Face Format](https://developer.android.com/training/wearables/wff)形式のXMLを出力する。  
Sassのようなもの。  

記述部分  
https://github.com/matsudamper/WearOS-WatchFaceFormat/blob/main/build-logic/src/main/kotlin/net/matsudamper/WatchFaceFormatGeneratorPlugin.kt  

# インストール方法
## Release
```shell
./gradlew assembleRelease
adb install .\app\build\outputs\apk\release\app-release.apk
adb shell am broadcast -a com.google.android.wearable.app.DEBUG_SURFACE --es operation set-watchface --es watchFaceId net.matsudamper.watchface
```

## Debug
```shell
./gradlew assembleDebug
adb install .\app\build\outputs\apk\debug\app-debug.apk
adb shell am broadcast -a com.google.android.wearable.app.DEBUG_SURFACE --es operation set-watchface --es watchFaceId net.matsudamper.watchface
```
