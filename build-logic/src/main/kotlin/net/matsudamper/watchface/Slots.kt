package net.matsudamper.watchface

import net.matsudamper.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.dsl.element.DefaultProvider
import net.matsudamper.dsl.scope.ComplicationSlot
import net.matsudamper.dsl.scope.SceneScope
import kotlin.math.cos
import kotlin.math.sin

@Suppress("FunctionName")
internal fun SceneScope.Slots(
    width: Int,
    height: Int,
) {
    val slotCount = 6
    val slotSize = (width * height) / 1500
    val slotMargin = slotSize

    val angleStep = 360 / slotCount
    for (i in 0 until slotCount) {
        val angle = i * angleStep + (angleStep / 2)
        ComplicationSlot(
            x = ((width - slotSize) / 2) + (cos(Math.toRadians(angle.toDouble())) * slotMargin).toInt(),
            y = ((height - slotSize) / 2) + (sin(Math.toRadians(angle.toDouble())) * slotMargin).toInt(),
            width = slotSize,
            height = slotSize,
            slotId = i,
            supportedTypes = listOf(
                ComplicationSlotSupportedType.RANGED_VALUE,
                ComplicationSlotSupportedType.SMALL_IMAGE,
                ComplicationSlotSupportedType.LONG_TEXT,
                ComplicationSlotSupportedType.SHORT_TEXT,
                ComplicationSlotSupportedType.MONOCHROMATIC_IMAGE,
                ComplicationSlotSupportedType.PHOTO_IMAGE,
                ComplicationSlotSupportedType.EMPTY,
            ),
            isCustomizable = true,
        ) {
            DefaultProviderPolicy(
                primaryProvider = "com.fitbit.FitbitMobile/com.fitbit.complications.calories.CaloriesComplicationDataSourceService",
                defaultSystemProviderType = ComplicationSlotSupportedType.RANGED_VALUE,
                defaultSystemProvider = DefaultProvider.STEP_COUNT,
            )
            BoundingOval(
                x = 0,
                y = 0,
                width = slotSize,
                height = slotSize,
                outlinePadding = 2,
            )
            Complication(type = ComplicationSlotSupportedType.RANGED_VALUE) {
                RangeValueLayout(
                    slotSize = slotSize,
                )
            }
            Complication(type = ComplicationSlotSupportedType.LONG_TEXT) {
                TextCompressionLayout(
                    slotSize = slotSize,
                )
            }
            Complication(type = ComplicationSlotSupportedType.SHORT_TEXT) {
                TextCompressionLayout(
                    slotSize = slotSize,
                )
            }
            Complication(type = ComplicationSlotSupportedType.PHOTO_IMAGE) {
                TextCompressionLayout(
                    slotSize = slotSize,
                )
            }
            Complication(type = ComplicationSlotSupportedType.SMALL_IMAGE) {
                SmallImageCompression(
                    slotSize = slotSize,
                )
            }
            Complication(type = ComplicationSlotSupportedType.MONOCHROMATIC_IMAGE) {
                TextCompressionLayout(
                    slotSize = slotSize,
                )
            }
        }
    }
}
