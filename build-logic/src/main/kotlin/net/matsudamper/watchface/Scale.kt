package net.matsudamper.watchface

import net.matsudamper.watchface.color.UserScaleColor
import net.matsudamper.watchface.dsl.element.Cap
import net.matsudamper.watchface.dsl.scope.Group
import net.matsudamper.watchface.dsl.scope.PartDraw
import net.matsudamper.watchface.dsl.scope.SceneScope
import net.matsudamper.watchface.dsl.scope.draw.Stroke


@Suppress("FunctionName")
internal fun SceneScope.Scale(
    width: Int,
    height: Int,
    maxStrokeSize: Int,
) {
    Group(
        x = 0,
        y = 0,
        width = width,
        height = height,
    ) {
        val step = 360 / 60
        for (i in 0 until 60) {
            val angle = i * step
            val isBig = i % 5 == 0
            val length = if (isBig) maxStrokeSize else maxStrokeSize / 2
            val border = if (isBig) 5 else 3
            PartDraw(
                x = 0,
                y = 0,
                width = width,
                height = height,
                pivotX = 0.5f,
                pivotY = 0.5f,
                angle = angle.toFloat(),
                tintColor = UserScaleColor.getColorSymbol(),
            ) {
                val startX = width / 2f
                Line(
                    startX = startX,
                    startY = 0f,
                    endX = startX,
                    endY = length.toFloat(),
                ) {
                    Stroke(
                        cap = if (isBig) {
                            Cap.ROUND
                        } else {
                            Cap.SQUARE
                        },
                        color = UserScaleColor.getColorSymbol(),
                        thickness = border,
                    )
                }
            }
        }
    }
}
