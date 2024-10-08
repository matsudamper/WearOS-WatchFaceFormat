package net.matsudamper.watchface

import net.matsudamper.watchface.color.UserAnalogHandColor
import net.matsudamper.watchface.color.UserAnalogSecondColor
import net.matsudamper.watchface.dsl.element.Cap
import net.matsudamper.watchface.dsl.element.SourceType
import net.matsudamper.watchface.dsl.element.VariantMode
import net.matsudamper.watchface.dsl.scope.Group
import net.matsudamper.watchface.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.watchface.dsl.scope.PartDraw
import net.matsudamper.watchface.dsl.scope.SceneScope
import net.matsudamper.watchface.dsl.scope.Variant
import net.matsudamper.watchface.dsl.scope.draw.Stroke
import net.matsudamper.watchface.dsl.scope.draw.Transform
import kotlin.math.roundToInt

@Suppress("FunctionName")
internal fun SceneScope.HourMinHand(
    width: Int,
    height: Int,
) {
    Group(
        x = 0,
        y = 0,
        width = width,
        height = height,
    ) {
        val strokeSize = (1f / 225 * width).roundToInt()
        Hand(
            width = width,
            height = height,
            angleTransform = "6 * ${SourceType.MINUTE.symbol}",
            strokeSize = strokeSize,
            hourWidth = width / (14 * 1.5).toInt(),
            padding = (width / 2f) / (5f / 1f),
            color = UserAnalogHandColor.getColorSymbol(),
        )
        Hand(
            width = width,
            height = height,
            angleTransform = "(60 * ${SourceType.HOUR_0_11.symbol} + ${SourceType.MINUTE.symbol}) / 2",
            strokeSize = strokeSize,
            padding = (width / 2f) / (5 / 3f),
            hourWidth = width / 14,
            color = UserAnalogHandColor.getColorSymbol(),
        )
    }
}

@Suppress("FunctionName")
internal fun HasWatchFaceLayoutElement.Hand(
    width: Int,
    height: Int,
    angleTransform: String,
    strokeSize: Int,
    padding: Float,
    hourWidth: Int,
    color: String,
) {
    PartDraw(
        x = 0,
        y = 0,
        width = width,
        height = height,
        pivotX = 0.5f,
        pivotY = 0.5f,
        angle = 0f,
    ) {
        Transform(
            target = "angle",
            value = angleTransform,
        )
        val defHourWidth = hourWidth - strokeSize
        RoundRectangle(
            x = (width - defHourWidth) / 2f,
            y = padding,
            width = defHourWidth.toFloat(),
            height = (height + hourWidth) / 2f - padding,
            cornerRadiusX = defHourWidth / 2f,
            cornerRadiusY = defHourWidth / 2f,
        ) {
            Stroke(
                cap = Cap.ROUND,
                color = color,
                thickness = strokeSize,
            )
        }
    }
}

@Suppress("FunctionName")
internal fun SceneScope.SecondHand(
    width: Int,
    height: Int,
    strokeSize: Int,
) {
    PartDraw(
        x = 0,
        y = 0,
        width = width,
        height = height,
        alpha = 255,
    ) {
        Variant(
            mode = VariantMode.AMBIENT,
            target = "alpha",
            value = "0",
        )
        Arc(
            centerX = this@PartDraw.width / 2f,
            centerY = this@PartDraw.height / 2f,
            width = this@PartDraw.width.toFloat() - strokeSize,
            height = this@PartDraw.height.toFloat() - strokeSize,
            startAngle = 0f,
            endAngle = 359.999f,
        ) {
            Stroke(
                cap = Cap.ROUND,
                color = "#4d408bf4",
                thickness = strokeSize,
            )
        }
        Arc(
            centerX = this@PartDraw.width / 2f,
            centerY = this@PartDraw.height / 2f,
            width = this@PartDraw.width.toFloat() - strokeSize,
            height = this@PartDraw.height.toFloat() - strokeSize,
            startAngle = 0f,
        ) {
            Transform(
                target = "endAngle",
                value = "6 * ${SourceType.SECOND_MILLISECOND.symbol}",
            )
            Stroke(
                cap = Cap.ROUND,
                color = UserAnalogSecondColor.getColorSymbol(),
                thickness = strokeSize,
            )
        }
    }
}
