package net.matsudamper.watchface.dsl.scope.configuration

import net.matsudamper.watchface.dsl.element.PhotosConfigType
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

/**
 * https://developer.android.com/reference/wear-os/wff/user-configuration/photos-configuration
 */
@WatchFaceDSLMarker
class PhotosConfigurationScope(
    id: String,
    configType: PhotosConfigType,
) : WatchFaceHasChildElement {
    override val elementName: String = "PhotosConfiguration"
    override val attributes: Map<String, String?> = mapOf(
        "id" to id,
        "configType" to configType.value,
    )
    override val children: List<WatchFaceElement> = listOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}
