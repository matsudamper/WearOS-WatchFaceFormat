package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceTextElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class ExpressionScope(
    name: String,
    value: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Expression"
    override val attributes: Map<String, String?> = mapOf(
        "name" to name,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf(
        WatchFaceTextElement(value)
    )
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
