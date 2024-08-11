package net.matsudamper.dsl.scope.complication

import net.matsudamper.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@Suppress("FunctionName")
@WatchFaceDSLMarker
class ComplicationScope(
    val type: ComplicationSlotSupportedType,
) : WatchFaceHasChildElement, HasWatchFaceLayoutElement {
    override val elementName: String = "Complication"
    override val attributes: Map<String, String?> = mapOf(
        "type" to type.value,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
