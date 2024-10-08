package net.matsudamper.watchface.dsl.element

enum class DefaultProvider(val value: String) {
    APP_SHORTCUT("APP_SHORTCUT"),
    DATE("DATE"),
    DAY_OF_WEEK("DAY_OF_WEEK"),
    FAVORITE_CONTACT("FAVORITE_CONTACT"),
    NEXT_EVENT("NEXT_EVENT"),
    STEP_COUNT("STEP_COUNT"),
    SUNRISE_SUNSET("SUNRISE_SUNSET"),
    TIME_AND_DATE("TIME_AND_DATE"),
    UNREAD_NOTIFICATION_COUNT("UNREAD_NOTIFICATION_COUNT"),
    WATCH_BATTERY("WATCH_BATTERY"),
    WORLD_CLOCK("WORLD_CLOCK"),
    DAY_AND_DATE("DAY_AND_DATE"),
    EMPTY("EMPTY"),
    ;
}
