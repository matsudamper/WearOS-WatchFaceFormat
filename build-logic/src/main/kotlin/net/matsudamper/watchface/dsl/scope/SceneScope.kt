package net.matsudamper.watchface.dsl.scope

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement

@WatchFaceDSLMarker
class SceneScope(
    val backgroundColor: String?,
) : WatchFaceHasChildElement, HasWatchFaceLayoutElement {
    override val elementName: String = "Scene"
    override val attributes: Map<String, String?> = mapOf(
        "backgroundColor" to backgroundColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
