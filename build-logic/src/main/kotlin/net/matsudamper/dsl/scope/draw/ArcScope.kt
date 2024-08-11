package net.matsudamper.dsl.scope.draw

import net.matsudamper.dsl.element.Direction
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class ArcScope(
    val centerX: Float,
    val centerY: Float,
    val height: Float,
    val width: Float,
    val startAngle: Float?,
    val endAngle: Float?,
    val direction: Direction,
) : WatchFaceHasChildElement, ShapeScope {
    override val elementName: String = "Arc"
    override val attributes: Map<String, String?> = mapOf(
        "centerX" to centerX.toString(),
        "centerY" to centerY.toString(),
        "height" to height.toString(),
        "width" to width.toString(),
        "startAngle" to startAngle?.toString(),
        "endAngle" to endAngle?.toString(),
        "direction" to direction.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

}
