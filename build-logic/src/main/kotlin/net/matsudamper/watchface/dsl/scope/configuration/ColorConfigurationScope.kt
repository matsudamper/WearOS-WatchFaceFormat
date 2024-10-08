package net.matsudamper.watchface.dsl.scope.configuration

import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@Suppress("FunctionName")
@WatchFaceDSLMarker
class ColorConfigurationScope(
    val id: String?,
    val displayName: String,
    val icon: String?,
    val screenReaderText: String?,
    val defaultValue: String
) : WatchFaceHasChildElement {
    override val elementName: String = "ColorConfiguration"
    override val attributes: Map<String, String?> = mapOf(
        "id" to id,
        "displayName" to displayName,
        "icon" to icon,
        "screenReaderText" to screenReaderText,
        "defaultValue" to defaultValue
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun ColorOption(
        id: String? = null,
        displayName: String,
        screenReaderText: String? = displayName,
        icon: String? = null,
        colors: List<String>
    ) {
        children.add(
            ColorOptionData(
                id = id,
                displayName = displayName,
                screenReaderText = screenReaderText,
                icon = icon,
                // 要素が1つだけの時、リアルタイムに色が反映されない為、末尾に追加している
                colors = colors.plus("#")
            )
        )
    }

    class ColorOptionData(
        id: String?,
        displayName: String,
        screenReaderText: String?,
        icon: String?,
        colors: List<String>
    ) : WatchFaceHasChildElement {
        override val elementName: String = "ColorOption"
        override val attributes: Map<String, String?> = mapOf(
            "id" to id,
            "displayName" to displayName,
            "screenReaderText" to screenReaderText,
            "icon" to icon,
            "colors" to colors.joinToString(" ")
        )
        override val children: MutableList<WatchFaceElement> = mutableListOf()
        override fun addChild(child: WatchFaceElement) {
            throw UnsupportedOperationException()
        }
    }
}
