package net.matsudamper.dsl.scope.draw


import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement


class StrokeScope(
    cap: Cap,
    color: String,
    thickness: Int,
) : WatchFaceHasChildElement {
    override val elementName: String = "Stroke"
    override val attributes: Map<String, String?> = mapOf(
        "cap" to cap.value,
        "color" to color,
        "thickness" to thickness.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}