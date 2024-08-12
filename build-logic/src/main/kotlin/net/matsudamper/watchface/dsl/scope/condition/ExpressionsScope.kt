package net.matsudamper.watchface.dsl.scope.condition

import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

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