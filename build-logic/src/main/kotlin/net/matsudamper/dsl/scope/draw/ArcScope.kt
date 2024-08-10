package net.matsudamper.dsl.scope.draw

import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.Direction
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

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
) : WatchFaceHasChildElement {
    override val elementName: String = "Arc"
    override val attributes: Map<String, String?> = mapOf(
        "centerX" to centerX.toString(),
        "centerY" to centerY.toString(),
        "height" to height.toString(),
        "width" to width.toString(),
        "startAngle" to startAngle?.toString(),
        "endAngle" to endAngle?.toString(),
        "direction" to direction.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    fun Transform(
        target: String,
        value: String,
    ) {
        children.add(
            TransformHasChildElement(
                target = target,
                value = value,
            )
        )
    }

    fun Stroke(
        cap: Cap,
        color: String,
        thickness: Int,
    ) {
        children.add(
            StrokeHasChildElement(
                cap = cap,
                color = color,
                thickness = thickness,
            )
        )
    }


    private class TransformHasChildElement(
        target: String,
        value: String,
    ) : WatchFaceHasChildElement {
        override val elementName: String = "Transform"
        override val attributes: Map<String, String?> = mapOf(
            "target" to target,
            "value" to value,
        )
        override val children: MutableList<WatchFaceElement> = mutableListOf()
    }

    private class StrokeHasChildElement(
        cap: Cap,
        color: String,
        thickness: Int,
    ) : WatchFaceHasChildElement {
        override val elementName: String = "Stroke"
        override val attributes: Map<String, String?> = mapOf(
            "cap" to cap.value,
            "color" to color,
            "thickness" to thickness.toString(),
        )
        override val children: MutableList<WatchFaceElement> = mutableListOf()
    }
}
