package net.matsudamper.watchface.color

object UserScaleColor {
    const val ID = "scaleColor"
    const val displayName: String = "color_name_scale"

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
