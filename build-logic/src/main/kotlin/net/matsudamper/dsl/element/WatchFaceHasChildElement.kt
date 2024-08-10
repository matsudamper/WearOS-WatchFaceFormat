package net.matsudamper.dsl.element

sealed interface WatchFaceElement

interface WatchFaceHasChildElement : WatchFaceElement {
    val elementName: String
    val attributes: Map<String, String?>
    val children: MutableList<WatchFaceElement>
}

data class WatchFaceTextElement(
    val text: String,
) : WatchFaceElement
