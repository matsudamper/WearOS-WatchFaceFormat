package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.ClipShape
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.metadata.MetadataKey
import net.matsudamper.dsl.metadata.MetadataValue

@Suppress("FunctionName")
@WatchFaceDSLMarker
class WatchFaceScope(
    val clipShape: ClipShape,
    val height: Int,
    val width: Int,
) : WatchFaceElement {
    override val elementName: String = "WatchFace"
    override val attributes: Map<String, String?> = mapOf(
        "clipShape" to clipShape.value,
        "height" to height.toString(),
        "width" to width.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()

    fun <T : MetadataValue> Metadata(key: MetadataKey<T>, value: T) {
        children.add(MetaData(key.name, value.value))
    }

    fun Scene(
        backgroundColor: String? = null,
        block: SceneScope.() -> Unit,
    ) {
        val scope = SceneScope(
            backgroundColor = backgroundColor,
        )
        block(scope)
        children.add(scope)
    }

    private data class MetaData(val key: String, val value: String) : WatchFaceElement {
        override val elementName: String = "Metadata"
        override val children: List<WatchFaceElement> = listOf()
        override val attributes: Map<String, String?> = mapOf(
            "key" to key,
            "value" to value,
        )
    }
}
