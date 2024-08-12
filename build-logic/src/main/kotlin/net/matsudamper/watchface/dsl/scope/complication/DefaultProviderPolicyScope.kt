package net.matsudamper.watchface.dsl.scope.complication

import net.matsudamper.watchface.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.watchface.dsl.element.DefaultProvider
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class DefaultProviderPolicyScope(
    primaryProvider: String?,
    defaultSystemProvider: DefaultProvider,
    defaultSystemProviderType: ComplicationSlotSupportedType,
) : WatchFaceHasChildElement {
    override val elementName: String = "DefaultProviderPolicy"
    override val attributes: Map<String, String?> = mapOf(
        "defaultSystemProvider" to defaultSystemProvider.value,
        "defaultSystemProviderType" to defaultSystemProviderType.value,
        "primaryProvider" to primaryProvider,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}
