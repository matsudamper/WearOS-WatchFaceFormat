package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
@Suppress("FunctionName")
class ExpressionsScope : WatchFaceHasChildElement {
    override val elementName: String = "Expressions"
    override val attributes: Map<String, String?> = mapOf()
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
    fun Expression(
        name: String,
        value: String,
    ) {
        children.add(
            ExpressionScope(
                name = name,
                value = value
            )
        )
    }
}