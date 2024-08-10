package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
@Suppress("FunctionName")
class CompareScope(
    expression: String
) : WatchFaceHasChildElement {
    override val elementName: String = "Compare"
    override val attributes: Map<String, String?> = mapOf(
        "expression" to expression,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
}
