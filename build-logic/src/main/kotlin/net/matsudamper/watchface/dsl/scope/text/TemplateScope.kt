package net.matsudamper.watchface.dsl.scope.text

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceTextElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
@Suppress("FunctionName")
class TemplateScope : WatchFaceHasChildElement {
    override val elementName: String = "Template"
    override val attributes: Map<String, String?> = mapOf()
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun addRawText(text: String) {
        children.add(WatchFaceTextElement(text))
    }

    fun Parameter(
        expression: String,
    ) {
        children.add(ParameterScope(expression))
    }
}
