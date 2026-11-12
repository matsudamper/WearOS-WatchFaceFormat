import java.lang.reflect.Method;
import java.util.Locale;

/**
 * root の取れないエミュレータでシステムロケールを変更するため、adb shell 権限（CHANGE_CONFIGURATION を持つ）で
 * app_process から実行する。Android の隠し API を使うため、ビルド時に android.jar を不要にする目的で全てリフレクションで呼ぶ。
 */
public class SetSystemLocale {
    public static void main(String[] args) throws Exception {
        String languageTag = args[0];

        Object activityManager = Class.forName("android.app.ActivityManager")
                .getMethod("getService")
                .invoke(null);
        Class<?> configurationClass = Class.forName("android.content.res.Configuration");
        Object configuration = activityManager.getClass()
                .getMethod("getConfiguration")
                .invoke(activityManager);

        Class<?> localeListClass = Class.forName("android.os.LocaleList");
        Object localeList = localeListClass
                .getConstructor(Locale[].class)
                .newInstance((Object) new Locale[]{Locale.forLanguageTag(languageTag)});
        configurationClass.getMethod("setLocales", localeListClass).invoke(configuration, localeList);
        configurationClass.getField("userSetLocale").setBoolean(configuration, true);

        Method withAttribution = findMethod(activityManager.getClass(), "updatePersistentConfigurationWithAttribution");
        if (withAttribution != null) {
            withAttribution.invoke(activityManager, configuration, "com.android.shell", null);
        } else {
            activityManager.getClass()
                    .getMethod("updatePersistentConfiguration", configurationClass)
                    .invoke(activityManager, configuration);
        }
        System.out.println("System locale set to " + languageTag);
    }

    private static Method findMethod(Class<?> target, String name) {
        for (Method method : target.getMethods()) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }
}
