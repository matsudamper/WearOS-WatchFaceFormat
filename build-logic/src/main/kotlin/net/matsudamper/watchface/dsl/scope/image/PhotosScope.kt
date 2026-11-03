package net.matsudamper.watchface.dsl.scope.image

import net.matsudamper.watchface.dsl.element.PhotoChangeTrigger
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

/**
 * https://developer.android.com/reference/wear-os/wff/group/part/image/photos
 */
@WatchFaceDSLMarker
class PhotosScope(
    source: String,
    defaultImageResource: String,
    change: List<PhotoChangeTrigger>?,
    changeAfterEvery: Int?,
    width: Int?,
    height: Int?,
) : WatchFaceHasChildElement {
    override val elementName: String = "Photos"
    override val attributes: Map<String, String?> = mapOf(
        "source" to source,
        "defaultImageResource" to defaultImageResource,
        "change" to change?.joinToString(" ") { it.value },
        "changeAfterEvery" to changeAfterEvery?.toString(),
        "width" to width?.toString(),
        "height" to height?.toString(),
    )
    override val children: List<WatchFaceElement> = listOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}
