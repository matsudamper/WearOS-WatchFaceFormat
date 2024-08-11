package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker


@WatchFaceDSLMarker
@Suppress("FunctionName")
class ConditionScope : WatchFaceHasChildElement, HasWatchFaceLayoutElement {
    override val elementName: String = "Condition"
    override val attributes: Map<String, String?> = mapOf()
    private val expressions = ExpressionsScope()
    private val mutableChildren = mutableListOf<WatchFaceElement>()
    override val children: List<WatchFaceElement> get() = listOf(expressions).plus(mutableChildren)
    override fun addChild(child: WatchFaceElement) {
        mutableChildren.add(child)
    }

    fun Expressions(
        block: ExpressionsScope.() -> Unit
    ) {
        expressions.apply(block)
    }

    fun Compare(
        expression: String,
        block: CompareScope.() -> Unit
    ) {
        mutableChildren.add(
            CompareScope(
                expression = expression
            ).apply(block)
        )
    }

    fun Default(
        block: DefaultScope.() -> Unit
    ) {
        mutableChildren.add(DefaultScope().apply(block))
    }
}
