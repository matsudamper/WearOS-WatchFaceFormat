package net.matsudamper.watchface.color

import net.matsudamper.watchface.dsl.scope.configuration.ColorOptionId

data class UserColorConfigurations(
    val resourceName: String,
    val colors: List<String>,
    val id: ColorOptionId,
)
