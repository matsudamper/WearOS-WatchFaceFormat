package net.matsudamper.dsl.scope.image

import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
@Suppress("FunctionName")
class PartImageScope(
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
) : WatchFaceHasChildElement {
    override val elementName: String = "PartImage"
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
        "renderMode" to renderMode?.name,
        "tintColor" to tintColor
    )

    override val children: MutableList<WatchFaceHasChildElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child as WatchFaceHasChildElement)
    }

    fun Image(
        resource: String,
    ) {
        children.add(
            ImageScope(
                resource = resource
            )
        )
    }

    fun ImageFilter(
        block: () -> Unit
    ) {
        TODO()
    }
}

