package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker
import net.matsudamper.dsl.scope.WatchFaceLayoutHasChildElement
import net.matsudamper.dsl.scope.WatchFaceHasChildLayoutReceiveScope


@WatchFaceDSLMarker
@Suppress("FunctionName")
class ConditionScope : WatchFaceHasChildElement, WatchFaceLayoutHasChildElement {
    override val elementName: String = "Condition"
    override val attributes: Map<String, String?> = mapOf()
    override val children: MutableList<WatchFaceElement> = mutableListOf()

    fun Expressions(
        block: ExpressionsScope.() -> Unit
    ) {
        children.add(ExpressionsScope().apply(block))
    }

    fun Compare(
        expression: String,
        block: CompareScope.() -> Unit
    ) {
        children.add(
            CompareScope(
                expression = expression
            ).apply(block)
        )
    }

    fun Default(
        block: WatchFaceHasChildLayoutReceiveScope.() -> Unit
    ) {
        children.add(DefaultScope().apply(block))
    }
}
