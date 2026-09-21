package net.matsudamper.watchface.dsl.scope.clock

import net.matsudamper.watchface.dsl.element.RenderMode
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class AnalogClockScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val pivotX: Float?,
    val pivotY: Float?,
    val angle: Float?,
    val alpha: Int?,
    val scaleX: Float?,
    val scaleY: Float?,
    val renderMode: RenderMode?,
    val tintColor: String?,
) : WatchFaceHasChildElement {
    override val elementName: String = "AnalogClock"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "pivotX" to pivotX?.toString(),
        "pivotY" to pivotY?.toString(),
        "angle" to angle?.toString(),
        "alpha" to alpha?.toString(),
        "scaleX" to scaleX?.toString(),
        "scaleY" to scaleY?.toString(),
        "renderMode" to renderMode?.value,
        "tintColor" to tintColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun HourHand(
        resource: String,
        x: Int? = null,
        y: Int? = null,
        width: Int? = null,
        height: Int? = null,
        pivotX: Float? = null,
        pivotY: Float? = null,
        tintColor: String? = null,
    ) {
        children.add(
            ClockHandScope(
                elementName = "HourHand",
                resource = resource,
                x = x,
                y = y,
                width = width,
                height = height,
                pivotX = pivotX,
                pivotY = pivotY,
                tintColor = tintColor,
            )
        )
    }

    fun MinuteHand(
        resource: String,
        x: Int? = null,
        y: Int? = null,
        width: Int? = null,
        height: Int? = null,
        pivotX: Float? = null,
        pivotY: Float? = null,
        tintColor: String? = null,
    ) {
        children.add(
            ClockHandScope(
                elementName = "MinuteHand",
                resource = resource,
                x = x,
                y = y,
                width = width,
                height = height,
                pivotX = pivotX,
                pivotY = pivotY,
                tintColor = tintColor,
            )
        )
    }

    fun SecondHand(
        resource: String,
        x: Int? = null,
        y: Int? = null,
        width: Int? = null,
        height: Int? = null,
        pivotX: Float? = null,
        pivotY: Float? = null,
        tintColor: String? = null,
    ) {
        children.add(
            ClockHandScope(
                elementName = "SecondHand",
                resource = resource,
                x = x,
                y = y,
                width = width,
                height = height,
                pivotX = pivotX,
                pivotY = pivotY,
                tintColor = tintColor,
            )
        )
    }
}

internal class ClockHandScope(
    override val elementName: String,
    resource: String,
    x: Int?,
    y: Int?,
    width: Int?,
    height: Int?,
    pivotX: Float?,
    pivotY: Float?,
    tintColor: String?,
) : WatchFaceHasChildElement {
    override val attributes: Map<String, String?> = mapOf(
        "resource" to resource,
        "x" to x?.toString(),
        "y" to y?.toString(),
        "width" to width?.toString(),
        "height" to height?.toString(),
        "pivotX" to pivotX?.toString(),
        "pivotY" to pivotY?.toString(),
        "tintColor" to tintColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
