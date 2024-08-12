package net.matsudamper.watchface

import net.matsudamper.watchface.dsl.element.Cap
import net.matsudamper.watchface.dsl.element.Complication
import net.matsudamper.watchface.dsl.element.TextAlign
import net.matsudamper.watchface.dsl.element.VariantMode
import net.matsudamper.watchface.dsl.scope.Group
import net.matsudamper.watchface.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.watchface.dsl.scope.PartDraw
import net.matsudamper.watchface.dsl.scope.PartImage
import net.matsudamper.watchface.dsl.scope.PartText
import net.matsudamper.watchface.dsl.scope.Variant
import net.matsudamper.watchface.dsl.scope.condition.Condition
import net.matsudamper.watchface.dsl.scope.draw.Stroke
import net.matsudamper.watchface.dsl.scope.draw.Transform
import net.matsudamper.watchface.dsl.scope.text.Font

@Suppress("FunctionName")
internal fun HasWatchFaceLayoutElement.RangeValueLayout(
    slotSize: Int,
) {
    Group(
        x = 0,
        y = 0,
        width = slotSize,
        height = slotSize,
    ) {
        RangeLayout(
            slotSize = slotSize,
        )
        TextCompressionLayout(
            slotSize = slotSize,
        )
    }
}
@Suppress("FunctionName")
internal fun HasWatchFaceLayoutElement.SmallImageCompression(
    slotSize: Int,
) {
    val hasAmbientId = "hasAmbientId"
    Condition {
        Expressions {
            Expression(
                name = hasAmbientId,
                value = "[${Complication.SMALL_IMAGE_AMBIENT}] != null",
            )
        }
        Compare(hasAmbientId) {
            Group(
                x = 0,
                y = 0,
                width = slotSize,
                height = slotSize,
            ) {
                SmallImage(
                    slotSize = slotSize,
                    image = Complication.SMALL_IMAGE,
                    ambientImage = Complication.SMALL_IMAGE_AMBIENT,
                )
            }
        }
        Default {
            Group(
                x = 0,
                y = 0,
                width = slotSize,
                height = slotSize,
            ) {
                SmallImage(
                    slotSize = slotSize,
                    image = Complication.SMALL_IMAGE,
                    ambientImage = Complication.SMALL_IMAGE,
                )
            }
        }
    }
}

@Suppress("FunctionName")
internal fun HasWatchFaceLayoutElement.TextCompressionLayout(
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
        Compare(hasTitleId) {
            Condition {
                TextTitleCompression(
                    slotSize = slotSize,
                )
            }
        }
        Default {
            Group(
                x = 0,
                y = 0,
                width = slotSize,
                height = slotSize,
            ) {
                Condition {
                    val hasPhotoImage = "hasPhotoImage"
                    val hasSmallAndAmbientImage = "hasSmallAndAmbientImage"
                    val hasSmallAndNoAmbientImage = "hasSmallAndNoAmbientImage"
                    Expressions {
                        Expression(
                            name = hasPhotoImage,
                            value = "[${Complication.PHOTO_IMAGE}] != null",
                        )
                        Expression(
                            name = hasSmallAndNoAmbientImage,
                            value = "[${Complication.SMALL_IMAGE}] != null && [${Complication.SMALL_IMAGE_AMBIENT}] == null",
                        )
                        Expression(
                            name = hasSmallAndAmbientImage,
                            value = "[${Complication.SMALL_IMAGE}] != null && [${Complication.SMALL_IMAGE_AMBIENT}] != null",
                        )
                    }
                    Compare(hasPhotoImage) {
                        Text(
                            slotSize = slotSize,
                            image = Complication.PHOTO_IMAGE,
                            ambientImage = Complication.PHOTO_IMAGE,
                        )
                    }
                    Compare(hasSmallAndAmbientImage) {
                        Text(
                            slotSize = slotSize,
                            image = Complication.SMALL_IMAGE,
                            ambientImage = Complication.SMALL_IMAGE_AMBIENT,
                        )
                    }
                    Compare(hasSmallAndNoAmbientImage) {
                        Text(
                            slotSize = slotSize,
                            image = Complication.SMALL_IMAGE,
                            ambientImage = Complication.SMALL_IMAGE,
                        )
                    }
                    Default {
                        Text(
                            slotSize = slotSize,
                            image = Complication.MONOCHROMATIC_IMAGE,
                            ambientImage = Complication.MONOCHROMATIC_IMAGE,
                        )
                    }
                }
            }
        }
    }
}

@Suppress("FunctionName")
private fun HasWatchFaceLayoutElement.RangeLayout(
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
            val strokeSize = 4
            Arc(
                centerX = this@PartDraw.width / 2f,
                centerY = this@PartDraw.height / 2f,
                width = this@PartDraw.width.toFloat() - strokeSize,
                height = this@PartDraw.height.toFloat() - strokeSize,
                startAngle = 0f,
                endAngle = 359.999f,
            ) {
                Transform(
                    target = "endAngle",
                    value = "360 * [${Complication.RANGED_VALUE_VALUE}] / ([${Complication.RANGED_VALUE_MAX}] - [${Complication.RANGED_VALUE_MIN}])",
                )
                Stroke(
                    cap = Cap.ROUND,
                    color = ContentColor.getColorSymbol(),
                    thickness = strokeSize,
                )
            }
        }
    }
}

@Suppress("FunctionName")
private fun HasWatchFaceLayoutElement.SmallImage(
    slotSize: Int,
    image: Complication,
    ambientImage: Complication,
) {
    val iconSize = slotSize / 2
    Group(
        x = (slotSize - iconSize) / 2,
        y = (slotSize - iconSize) / 2,
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
        ) {
            Variant(
                mode = VariantMode.AMBIENT,
                target = "alpha",
                value = "255",
            )
            Image(
                resource = "[$ambientImage]",
            )
        }
        PartImage(
            x = 0,
            y = 0,
            width = width,
            height = height,
            alpha = 255,
            renderMode = null,
        ) {
            Variant(
                mode = VariantMode.AMBIENT,
                target = "alpha",
                value = "0",
            )
            Image(
                resource = "[$image]",
            )
        }
    }
}

@Suppress("FunctionName")
private fun HasWatchFaceLayoutElement.Text(
    slotSize: Int,
    image: Complication,
    ambientImage: Complication,
) {
    Group(
        x = 0,
        y = 0,
        width = slotSize,
        height = slotSize,
    ) {
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
                    resource = "[$ambientImage]",
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
                    resource = "[$image]",
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
