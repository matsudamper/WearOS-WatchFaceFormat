package net.matsudamper.watchface.dsl.scope.condition

import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceTextElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

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
        WatchFaceTextElement("<![CDATA[$value]]>")
    )
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
