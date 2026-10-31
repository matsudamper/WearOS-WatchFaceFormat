package net.matsudamper.watchface.dsl.scope

import net.matsudamper.watchface.dsl.element.VariantMode
import net.matsudamper.watchface.dsl.element.VariantTransition
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement

@Suppress("FunctionName")
fun WatchFaceHasChildElement.Variant(
    mode: VariantMode,
    target: String,
    value: String,
    transition: VariantTransition?,
) {
    addChild(
        VariantScope(
            mode = mode,
            target = target,
            value = value,
            transition = transition,
        )
    )
}

@WatchFaceDSLMarker
class VariantScope(
    val mode: VariantMode,
    val target: String,
    val value: String,
    val transition: VariantTransition?,
) : WatchFaceHasChildElement {
    override val elementName: String = "Variant"
    override val attributes: Map<String, String?> = mapOf(
        "mode" to mode.value,
        "target" to target,
        "value" to value,
        "duration" to transition?.duration?.toString(),
        "startOffset" to transition?.startOffset?.toString(),
        "interpolation" to transition?.interpolation?.value,
    )
    override val children: List<WatchFaceElement> = emptyList()

    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException("VariantScope cannot have children")
    }
}
