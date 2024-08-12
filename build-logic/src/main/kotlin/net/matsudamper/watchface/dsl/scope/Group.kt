package net.matsudamper.watchface.dsl.scope

import net.matsudamper.watchface.dsl.element.RenderMode
import net.matsudamper.watchface.dsl.element.WatchFaceElement

class GroupScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val pivotX: Float?,
    val pivotY: Float?,
    val angle: Float?,
    val alpha: Int?,
    val name: String?,
    val renderMode: RenderMode?,
    val tintColor: String?
) : HasWatchFaceLayoutElement {
    override val elementName: String = "Group"
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
        "renderMode" to renderMode?.name,
        "tintColor" to tintColor
    )

    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
