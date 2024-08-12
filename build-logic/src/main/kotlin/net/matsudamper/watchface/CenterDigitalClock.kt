package net.matsudamper.watchface

import net.matsudamper.dsl.element.TextAlign
import net.matsudamper.dsl.scope.DigitalClock
import net.matsudamper.dsl.scope.SceneScope
import net.matsudamper.dsl.scope.text.Font

@Suppress("FunctionName")
internal fun SceneScope.CenterDigitalClock(
    width: Int,
    height: Int,
) {
    val fontHeight = height / 8
    DigitalClock(
        x = 0,
        y = (height - fontHeight) / 2,
        width = width,
        height = fontHeight,
    ) {
        TimeText(
            x = 0,
            y = 0,
            width = width,
            height = fontHeight,
            align = TextAlign.CENTER,
        ) {
            Font(
                color = ContentColor.getColorSymbol(),
                size = fontHeight,
            )
        }
    }
}
