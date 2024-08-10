package net.matsudamper.dsl.scope.condition

import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker
import net.matsudamper.dsl.scope.WatchFaceHasChildLayoutReceiveScope

@WatchFaceDSLMarker
@Suppress("FunctionName")
class DefaultScope : WatchFaceHasChildElement, WatchFaceHasChildLayoutReceiveScope {
    override val elementName: String = "Default"
    override val attributes: Map<String, String?> = mapOf()
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        TODO()
    }
}
