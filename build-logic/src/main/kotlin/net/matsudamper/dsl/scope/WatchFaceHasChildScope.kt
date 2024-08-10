package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.ClipShape
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.metadata.MetadataKey
import net.matsudamper.dsl.metadata.MetadataValue
import net.matsudamper.dsl.scope.configuration.UserConfigurationScope

@Suppress("FunctionName")
@WatchFaceDSLMarker
class WatchFaceHasChildScope(
    val clipShape: ClipShape,
    val height: Int,
    val width: Int,
) : WatchFaceHasChildElement {
    override val elementName: String = "WatchFace"
    override val attributes: Map<String, String?> = mapOf(
        "clipShape" to clipShape.value,
        "height" to height.toString(),
        "width" to width.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun <T : MetadataValue> Metadata(key: MetadataKey<T>, value: T) {
        children.add(MetaData(key.name, value.value))
    }

    fun Metadata(key: String, value: String) {
        children.add(MetaData(key, value))
    }

    fun UserConfiguration(
        block: UserConfigurationScope.() -> Unit,
    ) {
        val scope = UserConfigurationScope()
        block(scope)
        children.add(scope)
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

    private data class MetaData(val key: String, val value: String) : WatchFaceHasChildElement {
        override val elementName: String = "Metadata"
        override val children: MutableList<WatchFaceElement> = mutableListOf()
        override val attributes: Map<String, String?> = mapOf(
            "key" to key,
            "value" to value,
        )
        override fun addChild(child: WatchFaceElement) {
            throw UnsupportedOperationException()
        }
    }
}
