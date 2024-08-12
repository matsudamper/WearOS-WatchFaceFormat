package net.matsudamper.watchface.dsl.scope.text

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class ParameterScope(
    val expression: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Parameter"
    override val attributes: Map<String, String?> = mapOf(
        "expression" to expression,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}
