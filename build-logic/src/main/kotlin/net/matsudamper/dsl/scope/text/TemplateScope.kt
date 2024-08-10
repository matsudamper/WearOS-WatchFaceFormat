package net.matsudamper.dsl.scope.text

import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceTextElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

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
