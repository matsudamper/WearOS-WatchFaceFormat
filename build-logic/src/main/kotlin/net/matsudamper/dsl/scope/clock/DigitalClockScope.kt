package net.matsudamper.dsl.scope.clock

import net.matsudamper.dsl.element.HourFormat
import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.TextAlign
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker
import net.matsudamper.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.dsl.scope.text.HasFontElement

@WatchFaceDSLMarker
@Suppress("FunctionName")
class DigitalClockScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val pivotX: Float?,
    val pivotY: Float?,
    val angle: Float? = null,
    val alpha: Int = 255,
    val scaleX: Float?,
    val scaleY: Float?,
    val renderMode: RenderMode?,
    val tintColor: String? = null,
) : WatchFaceHasChildElement, HasWatchFaceLayoutElement, HasFontElement {
    override val elementName: String = "DigitalClock"
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    override val attributes: Map<String, String?>
        get() = mapOf(
            "x" to x.toString(),
            "y" to y.toString(),
            "width" to width.toString(),
            "height" to height.toString(),
            "pivotX" to pivotX?.toString(),
            "pivotY" to pivotY?.toString(),
            "angle" to angle?.toString(),
            "alpha" to alpha.toString(),
            "scaleX" to scaleX?.toString(),
            "scaleY" to scaleY?.toString(),
            "renderMode" to renderMode?.value,
            "tintColor" to tintColor,
        )

    fun TimeText(
        format: String = "hh:mm",
        hourFormat: HourFormat? = null,
        align: TextAlign,
        x: Int = 0,
        y: Int = 0,
        width: Int = 0,
        height: Int = 0,
        pivotX: Float? = null,
        pivotY: Float? = null,
        angle: Float? = null,
        alpha: Int? = null,
        tintColor: String? = null,
        block: TimeTextScope.() -> Unit,
    ) {
        val scope = TimeTextScope(
            format = format,
            hourFormat = hourFormat,
            align = align,
            x = x,
            y = y,
            width = width,
            height = height,
            pivotX = pivotX,
            pivotY = pivotY,
            angle = angle,
            alpha = alpha,
            tintColor = tintColor,
        )
        block(scope)
        children.add(scope)
    }
}
