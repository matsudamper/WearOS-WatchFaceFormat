package net.matsudamper.watchface

import net.matsudamper.watchface.color.UserDigitalTimeColor
import net.matsudamper.watchface.dsl.element.TextAlign
import net.matsudamper.watchface.dsl.scope.Group
import net.matsudamper.watchface.dsl.scope.PartText
import net.matsudamper.watchface.dsl.scope.SceneScope
import net.matsudamper.watchface.dsl.scope.text.Font

@Suppress("FunctionName")
internal fun SceneScope.AnalogClockNumber(
    width: Int,
    height: Int,
    margin: Int,
) {
    Group(
        x = 0,
        y = 0,
        width = width,
        height = height,
    ) {
        for (i in 1..12) {
            val angle = (360 / 12) * i
            Group(
                x = 0,
                y = 0,
                width = width,
                height = height,
                pivotX = 0.5f,
                pivotY = 0.5f,
                angle = angle.toFloat(),
            ) {
                val size = width / 16
                PartText(
                    x = 0,
                    y = margin + (size / 3),
                    width = width,
                    height = size,
                    angle = -angle.toFloat(),
                ) {
                    Text(
                        align = TextAlign.CENTER,
                    ) {
                        Font(
                            size = size,
                            color = UserDigitalTimeColor.getColorSymbol(),
                        ) {
                            Template {
                                addRawText("$i")
                            }
                        }
                    }
                }
            }
        }
    }
}
