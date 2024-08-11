package net.matsudamper.dsl.element

/**
 * https://developer.android.com/training/wearables/wff/common/attributes/source-type
 */
enum class SourceType(private val value: String) {
    UTC_TIMESTAMP("UTC_TIMESTAMP"),
    MILLISECOND("MILLISECOND"),
    SECOND("SECOND"),
    SECOND_Z("SECOND_Z"),
    SECOND_TENS_DIGIT("SECOND_TENS_DIGIT"),
    SECOND_UNITS_DIGIT("SECOND_UNITS_DIGIT"),
    SECOND_MILLISECOND("SECOND_MILLISECOND"),
    SECONDS_IN_DAY("SECONDS_IN_DAY"),
    MINUTE("MINUTE"),
    MINUTE_Z("MINUTE_Z"),
    MINUTE_TENS_DIGIT("MINUTE_TENS_DIGIT"),
    MINUTE_UNITS_DIGIT("MINUTE_UNITS_DIGIT"),
    MINUTE_SECOND("MINUTE_SECOND"),
    HOUR_0_11("HOUR_0_11"),
    HOUR_0_11_Z("HOUR_0_11_Z"),
    HOUR_0_11_MINUTE("HOUR_0_11_MINUTE"),
    HOUR_1_12("HOUR_1_12"),
    HOUR_1_12_Z("HOUR_1_12_Z"),
    HOUR_1_12_MINUTE("HOUR_1_12_MINUTE"),
    HOUR_0_23("HOUR_0_23"),
    HOUR_0_23_Z("HOUR_0_23_Z"),
    HOUR_0_23_MINUTE("HOUR_0_23_MINUTE"),
    HOUR_1_24("HOUR_1_24"),
    HOUR_1_24_Z("HOUR_1_24_Z"),
    HOUR_1_24_MINUTE("HOUR_1_24_MINUTE"),
    HOUR_TENS_DIGIT("HOUR_TENS_DIGIT"),
    HOUR_UNITS_DIGIT("HOUR_UNITS_DIGIT"),
    DAY("DAY"),
    DAY_Z("DAY_Z"),
    DAY_HOUR("DAY_HOUR"),
    DAY_0_30("DAY_0_30"),
    DAY_0_30_HOUR("DAY_0_30_HOUR"),
    DAY_OF_YEAR("DAY_OF_YEAR"),
    DAY_OF_WEEK("DAY_OF_WEEK"),
    DAY_OF_WEEK_F("DAY_OF_WEEK_F"),
    DAY_OF_WEEK_S("DAY_OF_WEEK_S"),
    FIRST_DAY_OF_WEEK("FIRST_DAY_OF_WEEK"),
    MONTH("MONTH"),
    MONTH_Z("MONTH_Z"),
    MONTH_F("MONTH_F"),
    MONTH_S("MONTH_S"),
    DAYS_IN_MONTH("DAYS_IN_MONTH"),
    MONTH_DAY("MONTH_DAY"),
    MONTH_0_11("MONTH_0_11"),
    MONTH_0_11_DAY("MONTH_0_11_DAY"),
    YEAR("YEAR"),
    YEAR_S("YEAR_S"),
    YEAR_MONTH("YEAR_MONTH"),
    YEAR_MONTH_DAY("YEAR_MONTH_DAY"),
    WEEK_IN_MONTH("WEEK_IN_MONTH"),
    WEEK_IN_YEAR("WEEK_IN_YEAR"),
    IS_24_HOUR_MODE("IS_24_HOUR_MODE"),
    IS_DAYLIGHT_SAVING_TIME("IS_DAYLIGHT_SAVING_TIME"),
    TIMEZONE("TIMEZONE"),
    TIMEZONE_ABB("TIMEZONE_ABB"),
    TIMEZONE_ID("TIMEZONE_ID"),
    TIMEZONE_OFFSET("TIMEZONE_OFFSET"),
    TIMEZONE_OFFSET_DST("TIMEZONE_OFFSET_DST"),
    AMPM_STATE("AMPM_STATE"),
    AMPM_POSITION("AMPM_POSITION"),
    AMPM_STRING("AMPM_STRING"),
    MOON_PHASE_POSITION("MOON_PHASE_POSITION"),
    MOON_PHASE_TYPE("MOON_PHASE_TYPE"),
    MOON_PHASE_TYPE_STRING("MOON_PHASE_TYPE_STRING"),
    STEP_COUNT("STEP_COUNT"),
    STEP_GOAL("STEP_GOAL"),
    STEP_PERCENT("STEP_PERCENT"),
    HEART_RATE("HEART_RATE"),
    HEART_RATE_Z("HEART_RATE_Z"),
    ACCELEROMETER_IS_SUPPORTED("ACCELEROMETER_IS_SUPPORTED"),
    ACCELEROMETER_X("ACCELEROMETER_X"),
    ACCELEROMETER_Y("ACCELEROMETER_Y"),
    ACCELEROMETER_Z("ACCELEROMETER_Z"),
    ACCELEROMETER_ANGLE_X("ACCELEROMETER_ANGLE_X"),
    ACCELEROMETER_ANGLE_Y("ACCELEROMETER_ANGLE_Y"),
    ACCELEROMETER_ANGLE_Z("ACCELEROMETER_ANGLE_Z"),
    ACCELEROMETER_ANGLE_XY("ACCELEROMETER_ANGLE_XY"),
    BATTERY_PERCENT("BATTERY_PERCENT"),
    BATTERY_CHARGING_STATUS("BATTERY_CHARGING_STATUS"),
    BATTERY_IS_LOW("BATTERY_IS_LOW"),
    BATTERY_TEMPERATURE_CELSIUS("BATTERY_TEMPERATURE_CELSIUS"),
    BATTERY_TEMPERATURE_FAHRENHEIT("BATTERY_TEMPERATURE_FAHRENHEIT"),
    UNREAD_NOTIFICATION_COUNT("UNREAD_NOTIFICATION_COUNT"),
    ;

    val symbol: String = "[$value]"
}
