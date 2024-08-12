package net.matsudamper.watchface.dsl.scope.clock

import net.matsudamper.watchface.dsl.element.FontFamily
import net.matsudamper.watchface.dsl.element.FontSlant
import net.matsudamper.watchface.dsl.element.FontWeight
import net.matsudamper.watchface.dsl.element.FontWidth
import net.matsudamper.watchface.dsl.element.HourFormat
import net.matsudamper.watchface.dsl.element.TextAlign
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker
import net.matsudamper.watchface.dsl.scope.text.FontScope
import net.matsudamper.watchface.dsl.scope.text.HasFontElement

@WatchFaceDSLMarker
@Suppress("FunctionName")
class TimeTextScope(
    val format: String,
    val hourFormat: HourFormat?,
    val align: TextAlign,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val pivotX: Float?,
    val pivotY: Float?,
    val angle: Float?,
    val alpha: Int?,
    val tintColor: String?,
) : WatchFaceHasChildElement, HasFontElement {
    override val elementName: String = "TimeText"
    override val attributes: Map<String, String?> = mapOf(
        "format" to format,
        "hourFormat" to hourFormat?.value,
        "align" to align.value,
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "pivotX" to pivotX?.toString(),
        "pivotY" to pivotY?.toString(),
        "angle" to angle?.toString(),
        "alpha" to alpha?.toString(),
        "tintColor" to tintColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

}
