package net.matsudamper.watchface.color

object UserDigitalTimeColor {
    const val ID = "digitalTimeColor"
    
    const val displayName: String = "digital time"

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
