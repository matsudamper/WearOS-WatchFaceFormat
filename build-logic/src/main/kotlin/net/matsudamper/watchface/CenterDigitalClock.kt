package net.matsudamper.watchface

import net.matsudamper.watchface.color.UserDigitalTimeColor
import net.matsudamper.watchface.dsl.element.TextAlign
import net.matsudamper.watchface.dsl.scope.DigitalClock
import net.matsudamper.watchface.dsl.scope.SceneScope
import net.matsudamper.watchface.dsl.scope.text.Font

@Suppress("FunctionName")
internal fun SceneScope.CenterDigitalClock(
    width: Int,
    height: Int,
) {
    val fontHeight = height / 9
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
                color = UserDigitalTimeColor.getColorSymbol(),
                size = fontHeight,
            )
        }
    }
}
