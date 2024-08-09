package net.matsudamper.dsl.scope.configuration

import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@Suppress("FunctionName")
@WatchFaceDSLMarker
class ColorConfigurationScope(
    val id: String?,
    val displayName: String,
    val icon: String?,
    val screenReaderText: String,
    val defaultValue: String
) : WatchFaceElement {
    override val elementName: String = "ColorConfiguration"
    override val attributes: Map<String, String?> = mapOf(
        "id" to id,
        "displayName" to displayName,
        "icon" to icon,
        "screenReaderText" to screenReaderText,
        "defaultValue" to defaultValue
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()

    fun ColorOption(
        id: String? = null,
        displayName: String,
        screenReaderText: String = displayName,
        icon: String? = null,
        colors: List<String>
    ) {
        children.add(
            ColorOptionData(
                id = id,
                displayName = displayName,
                screenReaderText = screenReaderText,
                icon = icon,
                colors = colors
            )
        )
    }

    class ColorOptionData(
        id: String?,
        displayName: String,
        screenReaderText: String,
        icon: String?,
        colors: List<String>
    ) : WatchFaceElement {
        override val elementName: String = "ColorOption"
        override val attributes: Map<String, String?> = mapOf(
            "id" to id,
            "displayName" to displayName,
            "screenReaderText" to screenReaderText,
            "icon" to icon,
            "colors" to colors.joinToString(" ")
        )
        override val children: List<WatchFaceElement> = listOf()
    }
}
