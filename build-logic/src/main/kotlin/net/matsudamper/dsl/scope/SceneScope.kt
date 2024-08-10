package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement

@WatchFaceDSLMarker
class SceneScope(
    val backgroundColor: String?,
) : WatchFaceHasChildElement, WatchFaceHasChildLayoutReceiveScope {
    override val elementName: String = "Scene"
    override val attributes: Map<String, String?> = mapOf(
        "backgroundColor" to backgroundColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
}
