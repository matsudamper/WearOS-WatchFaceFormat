package net.matsudamper.watchface.dsl.scope.text

import net.matsudamper.watchface.dsl.element.RenderMode
import net.matsudamper.watchface.dsl.element.TextAlign
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker
import net.matsudamper.watchface.dsl.scope.HasWatchFaceLayoutElement

@WatchFaceDSLMarker
@Suppress("FunctionName")
class PartTextScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val pivotX: Float?,
    val pivotY: Float?,
    val angle: Float?,
    val alpha: Int?,
    val name: String?,
    val scaleX: Float?,
    val scaleY: Float?,
    val renderMode: RenderMode?,
    val tintColor: String?,
) : HasWatchFaceLayoutElement {
    override val elementName: String = "PartText"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "pivotX" to pivotX?.toString(),
        "pivotY" to pivotY?.toString(),
        "angle" to angle?.toString(),
        "alpha" to alpha?.toString(),
        "name" to name,
        "scaleX" to scaleX?.toString(),
        "scaleY" to scaleY?.toString(),
        "renderMode" to renderMode?.value,
        "tintColor" to tintColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun Text(
        align: TextAlign? = null,
        ellipsis: Boolean = true,
        maxLines: Int? = null,
        block: TextScope.() -> Unit
    ) {
        val text = TextScope(
            align = align,
            ellipsis = ellipsis,
            maxLines = maxLines
        )
        text.block()
        children.add(text)
    }
}
