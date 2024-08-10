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
import net.matsudamper.dsl.scope.Group
import net.matsudamper.dsl.scope.PartText
import net.matsudamper.dsl.scope.SceneScope
import net.matsudamper.dsl.scope.clock.DigitalClock
import net.matsudamper.dsl.scope.complication.ComplicationSlot
import net.matsudamper.dsl.scope.condition.Condition
import net.matsudamper.dsl.scope.condition.DefaultScope
import net.matsudamper.dsl.scope.draw.PartDraw
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

    for (i in 0 until slotCount) {
        val angle = i * (360 / slotCount)
        ComplicationSlot(
            x = ((width - slotSize) / 2) + (cos(Math.toRadians(angle.toDouble())) * slotMargin).toInt(),
            y = ((height - slotSize) / 2) + (sin(Math.toRadians(angle.toDouble())) * slotMargin).toInt(),
            width = slotSize,
            height = slotSize,
            slotId = i,
            supportedTypes = ComplicationSlotSupportedType.values().toList(),
            isCustomizable = true,
        ) {
            DefaultProviderPolicy(
                primaryProvider = "com.google.android.gm/com.google.android.apps.gmail.wear.complications.UnreadEmailsComplicationService",
                defaultSystemProviderType = ComplicationSlotSupportedType.SHORT_TEXT,
                defaultSystemProvider = DefaultProvider.STEP_COUNT,
            )
            BoundingOval(
                x = 0,
                y = 0,
                width = slotSize,
                height = slotSize,
                outlinePadding = 2,
            )
            Complication(type = ComplicationSlotSupportedType.SHORT_TEXT) {
                Condition {
                    Default {
                        DefaultCompression(
                            slotSize = slotSize,
                        )
                    }
                }
            }
        }
    }
}

@Suppress("FunctionName")
private fun DefaultScope.DefaultCompression(
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
        PartText(
            x = 0,
            y = 0,
            width = slotSize,
            height = slotSize,
            alpha = 255,
        ) {
            Text(
                align = TextAlign.CENTER,
                ellipsis = true,
            ) {
                Font(
                    color = ContentColor.getColorSymbol(),
                    size = slotSize / 4,
                ) {
                    Template {
                        addRawText("%s")
                        Parameter(
                            expression = "[COMPLICATION.TEXT]",
                        )
                    }
                }
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
