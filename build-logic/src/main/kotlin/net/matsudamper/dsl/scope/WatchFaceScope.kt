package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.ClipShape
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.metadata.MetadataKey
import net.matsudamper.dsl.metadata.MetadataValue
import net.matsudamper.dsl.scope.toXmlAttribute

@Suppress("FunctionName")
@WatchFaceDSLMarker
class WatchFaceScope(
    val clipShape: ClipShape,
    val height: Int,
    val width: Int,
) : WatchFaceElement {
    private val metadata: MutableList<MetaData> = mutableListOf()
    fun <T : MetadataValue> Metadata(key: MetadataKey<T>, value: T) {
        metadata.add(MetaData(key.name, value.value))
    }

    private val screens: MutableList<SceneScope> = mutableListOf()
    fun Scene(
        backgroundColor: String? = null,
        block: SceneScope.() -> Unit,
    ) {
        val scope = SceneScope(
            backgroundColor = backgroundColor,
        )
        block(scope)
        screens.add(scope)
    }

    override fun getXml(): String {
        return buildString {
            appendLine("<WatchFace")
            append(
                listOf(
                    Pair("clipShape", clipShape.value),
                    Pair("height", height.toString()),
                    Pair("width", width.toString()),
                ).map { it.toXmlAttribute() }
                    .filter { it.isNotEmpty() }
                    .joinToString("\n")
            )
            appendLine(">")
            append(metadata.joinToString("\n") { it.getXml() })
            append(screens.joinToString("\n") { it.getXml() })
            appendLine("</WatchFace>")
        }
    }

    private data class MetaData(val key: String, val value: String) : WatchFaceElement {
        override fun getXml(): String {
            return buildString {
                appendLine("<Metadata")
                appendLine(Pair("key", key).toXmlAttribute())
                appendLine(Pair("value", value).toXmlAttribute())
                appendLine("/>")
            }
        }
    }
}
