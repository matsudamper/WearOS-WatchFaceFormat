package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceHasChildElement

@Suppress("FunctionName")
fun WatchFaceHasChildElement.Condition(
    block: ConditionScope.() -> Unit
) {
    addChild(ConditionScope().apply(block))
}
