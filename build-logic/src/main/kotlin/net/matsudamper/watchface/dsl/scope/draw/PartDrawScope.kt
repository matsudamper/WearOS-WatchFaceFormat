package net.matsudamper.watchface.dsl.scope.draw

import net.matsudamper.watchface.dsl.element.Direction
import net.matsudamper.watchface.dsl.element.RenderMode
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker
import net.matsudamper.watchface.dsl.scope.HasWatchFaceLayoutElement

@WatchFaceDSLMarker
@Suppress("FunctionName")
class PartDrawScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val name: String?,
    val angle: Float,
    val pivotX: Float,
    val pivotY: Float,
    val alpha: Int,
    val renderMode: RenderMode,
    val tintColor: String?,
) : WatchFaceHasChildElement, HasWatchFaceLayoutElement {
    override val elementName: String = "PartDraw"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "name" to name,
        "angle" to angle.toString(),
        "pivotX" to pivotX.toString(),
        "pivotY" to pivotY.toString(),
        "alpha" to alpha.toString(),
        "renderMode" to renderMode.value,
        "tintColor" to tintColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun Arc(
        centerX: Float,
        centerY: Float,
        height: Float,
        width: Float,
        endAngle: Float? = null,
        startAngle: Float? = null,
        direction: Direction = Direction.CLOCKWISE,
        block: ArcScope.() -> Unit,
    ) {
        val scope = ArcScope(
            centerX = centerX,
            centerY = centerY,
            height = height,
            width = width,
            startAngle = startAngle,
            endAngle = endAngle,
            direction = direction,
        )
        block(scope)
        children.add(scope)
    }

    fun RoundRectangle(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        cornerRadiusX: Float,
        cornerRadiusY: Float,
        block: RoundRectangleScope.() -> Unit,
    ) {
        val scope = RoundRectangleScope(
            x = x,
            y = y,
            width = width,
            height = height,
            cornerRadiusX = cornerRadiusX,
            cornerRadiusY = cornerRadiusY,
        )
        block(scope)
        children.add(scope)
    }
}
