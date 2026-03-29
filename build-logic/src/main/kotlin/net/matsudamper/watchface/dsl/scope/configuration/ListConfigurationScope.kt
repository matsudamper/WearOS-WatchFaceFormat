package net.matsudamper.watchface.dsl.scope.configuration

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@Suppress("FunctionName")
@WatchFaceDSLMarker
class ListConfigurationScope(
    val id: String?,
    val displayName: String,
    val icon: String?,
    val screenReaderText: String?,
    val defaultValue: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "ListConfiguration"
    override val attributes: Map<String, String?> = mapOf(
        "id" to id,
        "displayName" to displayName,
        "icon" to icon,
        "screenReaderText" to screenReaderText,
        "defaultValue" to defaultValue,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun ListOption(
        id: String,
        displayName: String,
        screenReaderText: String? = displayName,
        icon: String? = null,
    ) {
        children.add(ListOptionData(id, displayName, screenReaderText, icon))
    }

    class ListOptionData(
        id: String,
        displayName: String,
        screenReaderText: String?,
        icon: String?,
    ) : WatchFaceHasChildElement {
        override val elementName: String = "ListOption"
        override val attributes: Map<String, String?> = mapOf(
            "id" to id,
            "displayName" to displayName,
            "screenReaderText" to screenReaderText,
            "icon" to icon,
        )
        override val children: MutableList<WatchFaceElement> = mutableListOf()
        override fun addChild(child: WatchFaceElement) {
            throw UnsupportedOperationException()
        }
    }
}
