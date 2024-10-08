package net.matsudamper.watchface.dsl.scope.complication

import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class BoundingOvalScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val outlinePadding: Int,
) : WatchFaceHasChildElement {
    override val elementName: String = "BoundingOval"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "outlinePadding" to outlinePadding.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}
