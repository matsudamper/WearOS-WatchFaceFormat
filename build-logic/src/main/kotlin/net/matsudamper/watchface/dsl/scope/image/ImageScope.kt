package net.matsudamper.watchface.dsl.scope.image

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

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
