package net.matsudamper.dsl.scope.clock

import net.matsudamper.dsl.element.FontFamily
import net.matsudamper.dsl.element.FontSlant
import net.matsudamper.dsl.element.FontWeight
import net.matsudamper.dsl.element.FontWidth
import net.matsudamper.dsl.element.HourFormat
import net.matsudamper.dsl.element.TextAlign
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker
import net.matsudamper.dsl.scope.font.FontScope

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
) : WatchFaceHasChildElement {
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

    fun Font(
        family: FontFamily = FontFamily.SyncToDevice,
        size: Int,
        color: String,
        slant: FontSlant? = null,
        letterSpacing: Float = 0f,
        width: FontWidth? = null,
        weight: FontWeight = FontWeight.NORMAL,
    ) {
        children.add(
            FontScope(
                family = family,
                size = size,
                color = color,
                slant = slant,
                letterSpacing = letterSpacing,
                width = width,
                weight = weight,
            )
        )
    }
}
