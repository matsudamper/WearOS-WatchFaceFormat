package net.matsudamper.dsl.scope.complication

import net.matsudamper.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.dsl.scope.WatchFaceHasChildLayoutReceiveScope

@Suppress("FunctionName")
fun WatchFaceHasChildLayoutReceiveScope.ComplicationSlot(
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
    tintColor: String,
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
    children.add(scope)
}
