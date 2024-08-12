package net.matsudamper.watchface.dsl.scope.condition

import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class CompareScope(
    expression: String
) : WatchFaceHasChildElement, HasWatchFaceLayoutElement {
    override val elementName: String = "Compare"
    override val attributes: Map<String, String?> = mapOf(
        "expression" to expression,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
