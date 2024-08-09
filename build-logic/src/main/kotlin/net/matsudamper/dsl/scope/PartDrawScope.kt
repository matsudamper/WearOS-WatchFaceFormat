package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.Direction
import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.toXmlAttribute

@WatchFaceDSLMarker
@Suppress("FunctionName")
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
