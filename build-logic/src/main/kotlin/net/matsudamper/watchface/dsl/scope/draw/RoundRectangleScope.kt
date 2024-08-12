package net.matsudamper.watchface.dsl.scope.draw

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class RoundRectangleScope(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val cornerRadiusX: Float,
    val cornerRadiusY: Float,
) : WatchFaceHasChildElement, ShapeScope {
    override val elementName: String = "RoundRectangle"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "cornerRadiusX" to cornerRadiusX.toString(),
        "cornerRadiusY" to cornerRadiusY.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
