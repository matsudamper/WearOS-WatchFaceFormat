package net.matsudamper.dsl.scope.clock

import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.scope.WatchFaceHasChildLayoutReceiveScope

@Suppress("FunctionName")
fun WatchFaceHasChildLayoutReceiveScope.DigitalClock(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    pivotX: Float? = null,
    pivotY: Float? = null,
    angle: Float? = null,
    alpha: Int = 255,
    scaleX: Float? = null,
    scaleY: Float? = null,
    renderMode: RenderMode? = null,
    tintColor: String? = null,
    block: DigitalClockScope.() -> Unit,
) {
    val scope = DigitalClockScope(
        x = x,
        y = y,
        width = width,
        height = height,
        pivotX = pivotX,
        pivotY = pivotY,
        angle = angle,
        alpha = alpha,
        scaleX = scaleX,
        scaleY = scaleY,
        renderMode = renderMode,
        tintColor = tintColor,
    )
    block(scope)
    children.add(scope)
}
