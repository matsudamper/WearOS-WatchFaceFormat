package net.matsudamper.dsl.scope.draw

import net.matsudamper.dsl.element.Direction
import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

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
) : WatchFaceElement {
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
}
