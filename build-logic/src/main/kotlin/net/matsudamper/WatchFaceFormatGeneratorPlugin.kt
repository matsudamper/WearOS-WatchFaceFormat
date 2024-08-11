package net.matsudamper

import net.matsudamper.dsl.createWatchFace
import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.ClipShape
import net.matsudamper.dsl.element.ComplicationSlotSupportedType
import net.matsudamper.dsl.element.DefaultProvider
import net.matsudamper.dsl.element.SourceType
import net.matsudamper.dsl.element.TextAlign
import net.matsudamper.dsl.metadata.ClockType
import net.matsudamper.dsl.metadata.ClockTypeValue
import net.matsudamper.dsl.scope.ComplicationSlot
import net.matsudamper.dsl.scope.DigitalClock
import net.matsudamper.dsl.scope.Group
import net.matsudamper.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.dsl.scope.PartDraw
import net.matsudamper.dsl.scope.SceneScope
import net.matsudamper.dsl.scope.draw.Stroke
import net.matsudamper.dsl.scope.draw.Transform
import net.matsudamper.dsl.scope.text.Font
import org.gradle.api.Plugin
import org.gradle.api.Project
import kotlin.math.cos
import kotlin.math.sin

class WatchFaceFormatGeneratorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val generateFormat = tasks.register("generateFormat") {
                doLast {
                    runCatching {
                        val xml = generate()
                        layout.projectDirectory.asFile.resolve("src/main/res/raw/watchface.xml")
                            .writeText(xml)
                    }.onFailure {
                        it.printStackTrace()
                    }.getOrThrow()
                }
            }

            tasks.named("preBuild") {
                dependsOn(generateFormat)
            }
        }
    }
}

private fun generate(): String {
    return createWatchFace(
        width = 450,
        height = 450,
        clipShape = ClipShape.CIRCLE,
    ) {
        Metadata(
            key = ClockType,
            value = ClockTypeValue.ANALOG
        )
        Metadata(
            key = "PREVIEW_TIME",
            value = "10:08:32",
        )
        UserConfiguration {
            UserConfigurations(
                id = ContentColor.ID,
                displayName = "theme",
                defaultValue = ContentColor.Red.id,
            ) {
                ContentColor.values().forEach { color ->
                    ColorOption(
                        id = color.id,
                        displayName = color.resourceName,
                        colors = color.colors,
                    )
                }
            }
        }
        Scene {
            Slots(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
            )
            SecondHand(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
            )
            HourMinHand(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
            )
            CenterDigitalClock(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
            )
        }
    }
}

@Suppress("FunctionName")
private fun SceneScope.Slots(
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

@Suppress("FunctionName")
private fun SceneScope.CenterDigitalClock(
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

@Suppress("FunctionName")
private fun SceneScope.HourMinHand(
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
private fun HasWatchFaceLayoutElement.Hand(
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
private fun SceneScope.SecondHand(
    width: Int,
    height: Int,
) {
    PartDraw(
        x = 0,
        y = 0,
        width = width,
        height = height,
    ) {
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
