package net.matsudamper.watchface.dsl.scope.condition

import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement

@Suppress("FunctionName")
fun WatchFaceHasChildElement.Condition(
    block: ConditionScope.() -> Unit
) {
    addChild(ConditionScope().apply(block))
}
