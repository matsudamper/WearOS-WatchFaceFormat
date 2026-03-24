package net.matsudamper.watchface

import net.matsudamper.watchface.background.BackgroundImages
import net.matsudamper.watchface.dsl.scope.PartImage
import net.matsudamper.watchface.dsl.scope.SceneScope
import net.matsudamper.watchface.dsl.scope.condition.Condition

@Suppress("FunctionName")
internal fun SceneScope.Background(
    width: Int,
    height: Int,
) {
    val imageOptions = BackgroundImages.options.mapIndexedNotNull { idx, opt ->
        if (opt.drawableRes != null) idx to opt else null
    }
    if (imageOptions.isEmpty()) return

    Condition {
        Expressions {
            Expression(
                name = "backgroundIndex",
                value = "[CONFIGURATION.${BackgroundImages.CONFIGURATION_ID}]",
            )
        }
        imageOptions.forEach { (index, option) ->
            Compare(expression = "backgroundIndex == $index") {
                PartImage(x = 0, y = 0, width = width, height = height) {
                    Image(resource = option.drawableRes!!)
                }
            }
        }
        Default { }
    }
}
