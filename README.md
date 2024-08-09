# My Watchface
<img width="400" src="README/face.png">

# インストール方法
```shell
./gradlew assembleDebug
adb install .\app\build\outputs\apk\debug\app-debug.apk
adb shell am broadcast -a com.google.android.wearable.app.DEBUG_SURFACE --es operation set-watchface --es watchFaceId net.matsudamper.watchface
```
