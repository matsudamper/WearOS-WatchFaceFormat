package net.matsudamper.watchface

import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.SourceType
import net.matsudamper.dsl.element.VariantMode
import net.matsudamper.dsl.scope.Group
import net.matsudamper.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.dsl.scope.PartDraw
import net.matsudamper.dsl.scope.SceneScope
import net.matsudamper.dsl.scope.Variant
import net.matsudamper.dsl.scope.draw.Stroke
import net.matsudamper.dsl.scope.draw.Transform

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
        Hand(
            width = width,
            height = height,
            angleTransform = "6 * ${SourceType.MINUTE.symbol}",
            strokeSize = 2,
            hourWidth = width / (14 * 1.5).toInt(),
            padding = width / 10f,
        )
        Hand(
            width = width,
            height = height,
            angleTransform = "30 * ${SourceType.HOUR_0_11.symbol}",
            strokeSize = 2,
            hourWidth = width / 14,
            padding = width / 5f,
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
                color = ContentColor.getColorSymbol(),
                thickness = strokeSize,
            )
        }
    }
}

@Suppress("FunctionName")
internal fun SceneScope.SecondHand(
    width: Int,
    height: Int,
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
        val strokeSize = 10
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
                color = ContentColor.getColorSymbol(),
                thickness = strokeSize,
            )
        }
    }
}
