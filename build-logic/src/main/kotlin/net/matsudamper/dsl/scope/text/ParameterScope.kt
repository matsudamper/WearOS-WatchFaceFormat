package net.matsudamper.dsl.scope.text

import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

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
