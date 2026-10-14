package net.matsudamper.watchface.dsl.scope.draw

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class FillScope(
    color: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Fill"
    override val attributes: Map<String, String?> = mapOf(
        "color" to color,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}

@Suppress("FunctionName")
fun ShapeScope.Fill(
    color: String,
    block: FillScope.() -> Unit = {},
) {
    addChild(FillScope(color = color).apply(block))
}
