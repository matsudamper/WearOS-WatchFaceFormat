package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.toXmlAttribute

@WatchFaceDSLMarker
@Suppress("FunctionName")
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
