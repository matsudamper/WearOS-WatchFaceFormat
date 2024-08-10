package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement

@Suppress("FunctionName")
fun WatchFaceHasChildElement.Condition(
    block: ConditionScope.() -> Unit
) {
    children.add(ConditionScope().apply(block))
}
