package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.Direction
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.toXmlAttribute

@WatchFaceDSLMarker
@Suppress("FunctionName")
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
