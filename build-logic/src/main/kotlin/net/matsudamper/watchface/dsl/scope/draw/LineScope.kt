package net.matsudamper.watchface.dsl.scope.draw

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class LineScope(
    val startX: Float,
    val startY: Float,
    val endX: Float,
    val endY: Float,
) : WatchFaceHasChildElement, ShapeScope {
    override val elementName: String = "Line"
    override val attributes: Map<String, String?> = mapOf(
        "startX" to startX.toString(),
        "startY" to startY.toString(),
        "endX" to endX.toString(),
        "endY" to endY.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
