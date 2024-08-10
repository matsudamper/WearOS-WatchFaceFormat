package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.text.PartTextScope

interface WatchFaceHasChildLayoutReceiveScope : WatchFaceHasChildElement

@Suppress("FunctionName")
fun WatchFaceHasChildLayoutReceiveScope.PartText(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    pivotX: Float? = null,
    pivotY: Float? = null,
    angle: Float? = null,
    alpha: Int? = null,
    name: String? = null,
    scaleX: Float? = null,
    scaleY: Float? = null,
    renderMode: RenderMode? = null,
    tintColor: String? = null,
    block: PartTextScope.() -> Unit
) {
    addChild(
        PartTextScope(
            x = x,
            y = y,
            width = width,
            height = height,
            pivotX = pivotX,
            pivotY = pivotY,
            angle = angle,
            alpha = alpha,
            name = name,
            scaleX = scaleX,
            scaleY = scaleY,
            renderMode = renderMode,
            tintColor = tintColor
        ).apply(block)
    )
}

fun WatchFaceHasChildLayoutReceiveScope.Group(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    pivotX: Float? = null,
    pivotY: Float? = null,
    angle: Float? = null,
    alpha: Int? = null,
    name: String? = null,
    renderMode: RenderMode? = null,
    tintColor: String? = null,
    block: GroupScope.() -> Unit
) {
    addChild(
        GroupScope(
            x = x,
            y = y,
            width = width,
            height = height,
            pivotX = pivotX,
            pivotY = pivotY,
            angle = angle,
            alpha = alpha,
            name = name,
            renderMode = renderMode,
            tintColor = tintColor
        ).apply(block)
    )
}
