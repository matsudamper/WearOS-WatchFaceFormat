package net.matsudamper.watchface.dsl.element

sealed interface WatchFaceElement

interface WatchFaceHasChildElement : WatchFaceElement {
    val elementName: String
    val attributes: Map<String, String?>
    val children: List<WatchFaceElement>

    fun addChild(child: WatchFaceElement)
}

data class WatchFaceTextElement(
    val text: String,
) : WatchFaceElement
