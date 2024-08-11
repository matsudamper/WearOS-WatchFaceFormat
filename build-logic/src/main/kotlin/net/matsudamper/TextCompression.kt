package net.matsudamper

import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.Complication
import net.matsudamper.dsl.element.TextAlign
import net.matsudamper.dsl.element.VariantMode
import net.matsudamper.dsl.scope.Group
import net.matsudamper.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.dsl.scope.PartDraw
import net.matsudamper.dsl.scope.PartImage
import net.matsudamper.dsl.scope.PartText
import net.matsudamper.dsl.scope.Variant
import net.matsudamper.dsl.scope.condition.Condition
import net.matsudamper.dsl.scope.condition.DefaultScope
import net.matsudamper.dsl.scope.text.Font

@Suppress("FunctionName")
internal fun DefaultScope.TextCompression(
    slotSize: Int,
) {
    Condition {
        val hasTitleId = "hasTitleId"
        Expressions {
            Expression(
                name = hasTitleId,
                value = "[${Complication.TITLE}] != null",
            )
        }
        Compare(expression = hasTitleId) {
            TextTitleCompression(
                slotSize = slotSize,
            )
        }
        Default {
            Text(
                slotSize = slotSize,
            )
        }
    }
}

@Suppress("FunctionName")
private fun HasWatchFaceLayoutElement.Text(
    slotSize: Int,
) {
    Group(
        x = 0,
        y = 0,
        width = slotSize,
        height = slotSize,
    ) {
        PartDraw(
            x = 0,
            y = 0,
            width = slotSize,
            height = slotSize,
        ) {
            val strokeSize = 2
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
                    color = ContentColor.getColorSymbol(),
                    thickness = strokeSize,
                )
            }
        }
        val fontSize = slotSize / 6
        val iconSize = slotSize / 4
        val verticalPadding = (slotSize - iconSize - fontSize) / 2
        Group(
            x = (slotSize - iconSize) / 2,
            y = verticalPadding,
            width = iconSize,
            height = iconSize,
        ) {
            PartImage(
                x = 0,
                y = 0,
                width = width,
                height = height,
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
                width = width,
                height = height,
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
        PartText(
            x = 0,
            y = verticalPadding + iconSize,
            width = slotSize,
            height = fontSize,
            alpha = 255,
        ) {
            Text(
                align = TextAlign.CENTER,
                ellipsis = true,
            ) {
                Font(
                    color = ContentColor.getColorSymbol(),
                    size = fontSize,
                ) {
                    Template {
                        addRawText("%s")
                        Parameter(
                            expression = "[${Complication.TEXT}]",
                        )
                    }
                }
            }
        }
    }
}

@Suppress("FunctionName")
private fun HasWatchFaceLayoutElement.TextTitleCompression(
    slotSize: Int,
) {
    Group(
        x = 0,
        y = 0,
        width = slotSize,
        height = slotSize,
    ) {
        PartDraw(
            x = 0,
            y = 0,
            width = slotSize,
            height = slotSize,
        ) {
            val strokeSize = 2
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
                    color = ContentColor.getColorSymbol(),
                    thickness = strokeSize,
                )
            }
        }
        val fontSize = slotSize / 6
        val padding = (height - (fontSize * 2)) / 2
        PartText(
            x = 0,
            y = padding,
            width = slotSize,
            height = fontSize,
            alpha = 255,
        ) {
            Text(
                align = TextAlign.CENTER,
                ellipsis = true,
            ) {
                Font(
                    color = ContentColor.getColorSymbol(),
                    size = fontSize,
                ) {
                    Template {
                        addRawText("%s")
                        Parameter(
                            expression = "[${Complication.TITLE}]",
                        )
                    }
                }
            }
        }
        PartText(
            x = 0,
            y = padding + fontSize,
            width = slotSize,
            height = fontSize,
            alpha = 255,
        ) {
            Text(
                align = TextAlign.CENTER,
                ellipsis = true,
            ) {
                Font(
                    color = ContentColor.getColorSymbol(),
                    size = fontSize,
                ) {
                    Template {
                        addRawText("%s")
                        Parameter(
                            expression = "[${Complication.TEXT}]",
                        )
                    }
                }
            }
        }
    }
}
