@file:Suppress("FunctionName")

package net.matsudamper.dsl

import net.matsudamper.dsl.metadata.MetadataKey
import net.matsudamper.dsl.metadata.MetadataValue

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

@WatchFaceDSLMarker
class SceneScope(
    val backgroundColor: String?,
) : WatchFaceElement {
    private val partDraws: MutableList<PartDrawScope> = mutableListOf()
    fun PartDraw(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        name: String? = null,
        angle: Float = 0f,
        pivotX: Float = 0.5f,
        pivotY: Float = 0.5f,
        alpha: Int = 255,
        renderMode: RenderMode = RenderMode.ALL,
        tintColor: String? = null,
        block: PartDrawScope.() -> Unit,
    ) {
        val scope = PartDrawScope(
            x = x,
            y = y,
            width = width,
            height = height,
            name = name,
            angle = angle,
            pivotX = pivotX,
            pivotY = pivotY,
            alpha = alpha,
            renderMode = renderMode,
            tintColor = tintColor,
        )
        block(scope)
        partDraws.add(scope)
    }

    override fun getXml(): String {
        return buildString {
            appendLine("<Scene")
            appendLine(Pair("backgroundColor", backgroundColor).toXmlAttribute())
            appendLine(">")
            append(partDraws.joinToString("\n") { it.getXml() })
            appendLine("</Scene>")
        }
    }
}

@WatchFaceDSLMarker
class PartDrawScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val name: String?,
    val angle: Float,
    val pivotX: Float,
    val pivotY: Float,
    val alpha: Int,
    val renderMode: RenderMode,
    val tintColor: String?,
) : WatchFaceElement {
    private val arcs: MutableList<ArcScope> = mutableListOf()

    fun Arc(
        centerX: Float,
        centerY: Float,
        height: Float,
        width: Float,
        endAngle: Float? = null,
        startAngle: Float? = null,
        direction: Direction = Direction.CLOCKWISE,
        block: ArcScope.() -> Unit,
    ) {
        val scope = ArcScope(
            centerX = centerX,
            centerY = centerY,
            height = height,
            width = width,
            startAngle = startAngle,
            endAngle = endAngle,
            direction = direction,
        )
        block(scope)
        arcs.add(scope)
    }

    override fun getXml(): String {
        return buildString {
            appendLine("<PartDraw")
            append(
                listOf(
                    Pair("x", x.toString()),
                    Pair("y", y.toString()),
                    Pair("width", width.toString()),
                    Pair("height", height.toString()),
                    Pair("name", name),
                    Pair("angle", angle.toString()),
                    Pair("pivotX", pivotX.toString()),
                    Pair("pivotY", pivotY.toString()),
                    Pair("alpha", alpha.toString()),
                    Pair("renderMode", renderMode.toString()),
                    Pair("tintColor", tintColor),
                ).map { it.toXmlAttribute() }
                    .filter { it.isNotEmpty() }
                    .joinToString("\n")
            )
            appendLine(">")
            append(arcs.joinToString("\n") { it.getXml() })
            appendLine("</PartDraw>")
        }
    }
}

@WatchFaceDSLMarker
class ArcScope(
    val centerX: Float,
    val centerY: Float,
    val height: Float,
    val width: Float,
    val startAngle: Float?,
    val endAngle: Float?,
    val direction: Direction,
) : WatchFaceElement {
    private val transforms: MutableList<TransformElement> = mutableListOf()
    fun Transform(
        target: String,
        value: String,
    ) {
        transforms.add(
            TransformElement(
                target = target,
                value = value,
            )
        )
    }

    private val strokes: MutableList<StrokeElement> = mutableListOf()
    fun Stroke(
        cap: Cap,
        color: String,
        thickness: Int,
    ) {
        strokes.add(
            StrokeElement(
                cap = cap,
                color = color,
                thickness = thickness,
            )
        )
    }

    override fun getXml(): String {
        return buildString {
            appendLine("<Arc")
            append(
                listOf(
                    Pair("centerX", centerX.toString()),
                    Pair("centerY", centerY.toString()),
                    Pair("height", height.toString()),
                    Pair("width", width.toString()),
                    Pair("startAngle", startAngle?.toString()),
                    Pair("endAngle", endAngle?.toString()),
                    Pair("direction", direction.toString()),
                ).map { it.toXmlAttribute() }
                    .filter { it.isNotEmpty() }
                    .joinToString("\n")
            )
            appendLine(">")
            append(transforms.joinToString("\n") { it.getXml() })
            append(strokes.joinToString("\n") { it.getXml() })
            appendLine("</Arc>")
        }
    }

    private class TransformElement(
        private val target: String,
        private val value: String,
    ) : WatchFaceElement {
        override fun getXml(): String {
            return buildString {
                appendLine("<Transform")
                append(
                    listOf(
                        Pair("target", target),
                        Pair("value", value),
                    ).map { it.toXmlAttribute() }
                        .filter { it.isNotEmpty() }
                        .joinToString("\n")
                )
                appendLine("/>")
            }
        }
    }

    private class StrokeElement(
        private val cap: Cap,
        private val color: String,
        private val thickness: Int,
    ) : WatchFaceElement {
        override fun getXml(): String {
            return buildString {
                appendLine("<Stroke")
                append(
                    listOf(
                        Pair("cap", cap.value),
                        Pair("color", color),
                        Pair("thickness", thickness.toString()),
                    ).map { it.toXmlAttribute() }
                        .filter { it.isNotEmpty() }
                        .joinToString("\n")
                )
                appendLine("/>")
            }
        }
    }
}

fun createWatchFace(
    clipShape: ClipShape,
    height: Int,
    width: Int,
    block: WatchFaceScope.() -> Unit,
) : String {
    val scope = WatchFaceScope(
        clipShape = clipShape,
        height = height,
        width = width,
    )
    block(scope)
    return scope.getXml()
}

fun Pair<String, String?>.toXmlAttribute(): String {
    second ?: return ""
    return """$first="$second""""
}
