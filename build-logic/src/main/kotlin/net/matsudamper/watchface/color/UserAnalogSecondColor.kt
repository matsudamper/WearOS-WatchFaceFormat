package net.matsudamper.watchface.color

object UserAnalogSecondColor {
    const val ID = "analogSecondColor"
    const val displayName: String = "color_name_analog_seconds"

    fun values(): List<UserColorConfigurations> {
        return Colors.values().map { color ->
            UserColorConfigurations(
                id = color.id,
                resourceName = color.resourceName,
                colors = listOf(color.color)
            )
        }
    }

    fun getColorSymbol(): String {
        return "[CONFIGURATION.$ID.0]"
    }
}
