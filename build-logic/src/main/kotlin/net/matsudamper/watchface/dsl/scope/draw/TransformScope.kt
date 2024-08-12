package net.matsudamper.watchface.dsl.scope.draw

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement

class TransformScope(
    target: String,
    value: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Transform"
    override val attributes: Map<String, String?> = mapOf(
        "target" to target,
        "value" to value,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}
