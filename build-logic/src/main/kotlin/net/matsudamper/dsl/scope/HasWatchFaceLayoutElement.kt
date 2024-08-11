package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.scope.clock.DigitalClockScope
import net.matsudamper.dsl.scope.complication.ComplicationSlotScope
import net.matsudamper.dsl.scope.draw.PartDrawScope
import net.matsudamper.dsl.scope.image.PartImageScope
import net.matsudamper.dsl.scope.text.PartTextScope

/**
 * レイアウトに関する子を持てる
 * TODO一覧
 *         <PartImage ... />
 *         <PartAnimatedImage ... />
 *         <AnalogClock ... />
 */
interface HasWatchFaceLayoutElement : WatchFaceHasChildElement

@Suppress("FunctionName")
fun HasWatchFaceLayoutElement.PartText(
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

@Suppress("FunctionName")
fun HasWatchFaceLayoutElement.Group(
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

@Suppress("FunctionName")
fun HasWatchFaceLayoutElement.PartImage(
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
    block: PartImageScope.() -> Unit
) {
    addChild(
        PartImageScope(
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
        ).apply(
            block
        )
    )
}

@Suppress("FunctionName")
fun HasWatchFaceLayoutElement.PartDraw(
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
    addChild(scope)
}

@Suppress("FunctionName")
fun HasWatchFaceLayoutElement.DigitalClock(
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
    addChild(scope)
}

@Suppress("FunctionName")
fun HasWatchFaceLayoutElement.ComplicationSlot(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    pivotX: Float? = null,
    pivotY: Float? = null,
    angle: Float? = null,
    alpha: Int? = null,
    slotId: Int? = null,
    name: String? = null,
    displayName: String? = null,
    scaleX: Float? = null,
    scaleY: Float? = null,
    supportedTypes: List<ComplicationSlotSupportedType>,
    isCustomizable: Boolean,
    tintColor: String? = null,
    block: ComplicationSlotScope.() -> Unit,
) {
    val scope = ComplicationSlotScope(
        x = x,
        y = y,
        width = width,
        height = height,
        pivotX = pivotX,
        pivotY = pivotY,
        angle = angle,
        alpha = alpha,
        slotId = slotId,
        name = name,
        displayName = displayName,
        scaleX = scaleX,
        scaleY = scaleY,
        supportedTypes = supportedTypes,
        isCustomizable = isCustomizable,
        tintColor = tintColor,
    )
    block(scope)
    addChild(scope)
}
