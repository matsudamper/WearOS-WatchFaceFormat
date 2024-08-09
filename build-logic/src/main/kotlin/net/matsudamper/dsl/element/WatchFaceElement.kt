package net.matsudamper.dsl.element

interface WatchFaceElement {
    val elementName: String
    val attributes: Map<String, String?>
    val children: List<WatchFaceElement>
}
