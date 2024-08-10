package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.VariantMode
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement

@Suppress("FunctionName")
fun WatchFaceHasChildElement.Variant(
    mode: VariantMode,
    target: String,
    value: String,
) {
    addChild(
        VariantScope(
            mode = mode,
            target = target,
            value = value,
        )
    )
}

@WatchFaceDSLMarker
class VariantScope(
    val mode: VariantMode,
    val target: String,
    val value: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Variant"
    override val attributes: Map<String, String?> = mapOf(
        "mode" to mode.value,
        "target" to target,
        "value" to value,
    )
    override val children: List<WatchFaceElement> = emptyList()

    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException("VariantScope cannot have children")
    }
}
