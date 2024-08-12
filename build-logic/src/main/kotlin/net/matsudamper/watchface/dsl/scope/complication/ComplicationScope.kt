package net.matsudamper.watchface.dsl.scope.complication

import net.matsudamper.watchface.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

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
