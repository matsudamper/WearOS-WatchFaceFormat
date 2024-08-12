package net.matsudamper.watchface.dsl.scope.complication

import net.matsudamper.watchface.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.watchface.dsl.element.DefaultProvider
import net.matsudamper.watchface.dsl.element.VariantMode
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@Suppress("FunctionName")
@WatchFaceDSLMarker
class ComplicationSlotScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val pivotX: Float?,
    val pivotY: Float?,
    val angle: Float?,
    val alpha: Int?,
    val slotId: Int?,
    val name: String?,
    val displayName: String?,
    val scaleX: Float?,
    val scaleY: Float?,
    val supportedTypes: List<ComplicationSlotSupportedType>,
    val isCustomizable: Boolean,
    val tintColor: String?,
) : WatchFaceHasChildElement {
    override val elementName: String = "ComplicationSlot"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "pivotX" to pivotX?.toString(),
        "pivotY" to pivotY?.toString(),
        "angle" to angle?.toString(),
        "alpha" to alpha?.toString(),
        "slotId" to slotId?.toString(),
        "name" to name,
        "displayName" to displayName,
        "scaleX" to scaleX?.toString(),
        "scaleY" to scaleY?.toString(),
        "supportedTypes" to supportedTypes.joinToString(" ") { it.value },
        "isCustomizable" to isCustomizable.toString(),
        "tintColor" to tintColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun DefaultProviderPolicy(
        defaultSystemProvider: DefaultProvider = DefaultProvider.EMPTY,
        defaultSystemProviderType: ComplicationSlotSupportedType,
        primaryProvider: String? = null,
    ) {
        children.add(
            DefaultProviderPolicyScope(
                defaultSystemProvider = defaultSystemProvider,
                defaultSystemProviderType = defaultSystemProviderType,
                primaryProvider = primaryProvider,
            )
        )
    }

    fun BoundingOval(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        outlinePadding: Int,
    ) {
        children.add(
            BoundingOvalScope(
                x = x,
                y = y,
                width = width,
                height = height,
                outlinePadding = outlinePadding,
            )
        )
    }

    fun BoundingRoundBox(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        outlinePadding: Int,
    ) {
        children.add(
            BoundingRoundBoxScope(
                x = x,
                y = y,
                width = width,
                height = height,
                outlinePadding = outlinePadding,
            )
        )
    }

    fun Complication(
        type: ComplicationSlotSupportedType,
        block: ComplicationScope.() -> Unit
    ) {
        val scope = ComplicationScope(
            type = type,
        )
        scope.block()
        children.add(scope)
    }
}
