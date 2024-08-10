package net.matsudamper.dsl.scope.draw

import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.scope.WatchFaceHasChildLayoutReceiveScope

@Suppress("FunctionName")
fun WatchFaceHasChildLayoutReceiveScope.PartDraw(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    name: String? = null,
    angle: Float = 0f,
    pivotX: Float = 0.5f,
    pivotY: Float = 0.5f,
    alpha: Int = 255,
    renderMode: RenderMode = RenderMode.SOURCE,
    tintColor: String? = null,
    block: PartDrawScope.() -> Unit,
) {
    val scope = PartDrawScope(
        x = x,
        y = y,
        width = width,
        height = height,
        name = name,
        angle = angle,
        pivotX = pivotX,
        pivotY = pivotY,
        alpha = alpha,
        renderMode = renderMode,
        tintColor = tintColor,
    )
    block(scope)
    children.add(scope)
}
