package net.matsudamper.watchface.dsl.scope.draw

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class RectangleScope(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
) : WatchFaceHasChildElement, ShapeScope {
    override val elementName: String = "Rectangle"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
