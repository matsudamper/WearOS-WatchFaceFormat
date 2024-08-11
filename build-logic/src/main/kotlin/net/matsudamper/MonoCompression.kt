package net.matsudamper

import net.matsudamper.dsl.element.Complication
import net.matsudamper.dsl.element.VariantMode
import net.matsudamper.dsl.scope.Group
import net.matsudamper.dsl.scope.PartImage
import net.matsudamper.dsl.scope.Variant
import net.matsudamper.dsl.scope.condition.Condition
import net.matsudamper.dsl.scope.condition.ConditionScope

@Suppress("FunctionName")
internal fun ConditionScope.MonoCompression(
    slotSize: Int,
) {
    val hasMonoImage = "hasMonoImage"
    val hasMonoAmbient = "hasMonoAmbient"
    Expressions {
        Expression(
            name = hasMonoImage,
            value = "[${Complication.MONOCHROMATIC_IMAGE}] != null",
        )
    }
    Compare(expression = hasMonoImage) {
        val imageSize = slotSize / 2
        Group(
            x = (slotSize - imageSize) / 2,
            y = (slotSize - imageSize) / 2,
            width = slotSize,
            height = slotSize,
        ) {
            Condition {
                Expressions {
                    Expression(
                        name = hasMonoAmbient,
                        value = "[${Complication.MONOCHROMATIC_IMAGE_AMBIENT}] != null",
                    )
                }
                Compare(expression = hasMonoAmbient) {
                    PartImage(
                        x = 0,
                        y = 0,
                        width = imageSize,
                        height = imageSize,
                        alpha = 0,
                        renderMode = null,
                        tintColor = ContentColor.getColorSymbol(),
                    ) {
                        Variant(
                            mode = VariantMode.AMBIENT,
                            target = "alpha",
                            value = "255",
                        )
                        Image(
                            resource = "[${Complication.MONOCHROMATIC_IMAGE_AMBIENT}]",
                        )
                    }
                    PartImage(
                        x = 0,
                        y = 0,
                        width = imageSize,
                        height = imageSize,
                        alpha = 255,
                        renderMode = null,
                        tintColor = ContentColor.getColorSymbol(),
                    ) {
                        Variant(
                            mode = VariantMode.AMBIENT,
                            target = "alpha",
                            value = "0",
                        )
                        Image(
                            resource = "[${Complication.MONOCHROMATIC_IMAGE}]",
                        )
                    }
                }
                Default {
                    PartImage(
                        x = 0,
                        y = 0,
                        width = imageSize,
                        height = imageSize,
                        alpha = 255,
                        renderMode = null,
                        tintColor = ContentColor.getColorSymbol(),
                    ) {
                        Image(
                            resource = "[${Complication.MONOCHROMATIC_IMAGE}]",
                        )
                    }
                }
            }
        }
    }
}
