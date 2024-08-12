package net.matsudamper.watchface

data class UserColorConfigurations(
    val resourceName: String,
    val colors: List<String>,
    val id: String,
)

object UserContentColor {
    const val ID = "contentColor"

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
