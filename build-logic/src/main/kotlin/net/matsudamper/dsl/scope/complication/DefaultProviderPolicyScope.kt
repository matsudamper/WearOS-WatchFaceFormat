package net.matsudamper.dsl.scope.complication

import net.matsudamper.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.dsl.element.DefaultProvider
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

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
