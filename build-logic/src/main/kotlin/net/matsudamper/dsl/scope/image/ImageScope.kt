package net.matsudamper.dsl.scope.image

import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
@Suppress("FunctionName")
class ImageScope(
    resource: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Image"
    override val attributes: Map<String, String?> = mapOf(
        "resource" to resource
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
