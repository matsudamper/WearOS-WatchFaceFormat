package net.matsudamper.watchface.color

object UserContentColor {
    const val ID = "contentColor"
    const val displayName: String = "color_name_content"

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
