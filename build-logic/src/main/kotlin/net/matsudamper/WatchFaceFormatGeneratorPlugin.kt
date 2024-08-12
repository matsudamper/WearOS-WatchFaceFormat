package net.matsudamper

import net.matsudamper.watchface.AnalogClockNumber
import net.matsudamper.watchface.dsl.createWatchFace
import net.matsudamper.watchface.dsl.element.ClipShape
import net.matsudamper.watchface.dsl.metadata.ClockType
import net.matsudamper.watchface.dsl.metadata.ClockTypeValue
import net.matsudamper.watchface.CenterDigitalClock
import net.matsudamper.watchface.color.Colors
import net.matsudamper.watchface.color.UserContentColor
import net.matsudamper.watchface.HourMinHand
import net.matsudamper.watchface.Scale
import net.matsudamper.watchface.SecondHand
import net.matsudamper.watchface.Slots
import net.matsudamper.watchface.color.UserAnalogHandColor
import net.matsudamper.watchface.color.UserAnalogSecondColor
import net.matsudamper.watchface.color.UserDigitalTimeColor
import org.gradle.api.Plugin
import org.gradle.api.Project
import kotlin.math.roundToInt

class WatchFaceFormatGeneratorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val generateWatchFace = tasks.register("generateWatchFace") {
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
                dependsOn(generateWatchFace)
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
                id = UserContentColor.ID,
                displayName = UserContentColor.displayName,
                defaultValue = Colors.White.id,
            ) {
                UserContentColor.values().forEach { color ->
                    ColorOption(
                        id = color.id,
                        displayName = color.resourceName,
                        colors = color.colors,
                    )
                }
            }
            UserConfigurations(
                id = UserDigitalTimeColor.ID,
                displayName = UserDigitalTimeColor.displayName,
                defaultValue = Colors.White.id,
            ) {
                UserDigitalTimeColor.values().forEach { color ->
                    ColorOption(
                        id = color.id,
                        displayName = color.resourceName,
                        colors = color.colors,
                    )
                }
            }
            UserConfigurations(
                id = UserAnalogSecondColor.ID,
                displayName = UserAnalogSecondColor.displayName,
                defaultValue = Colors.White.id,
            ) {
                UserAnalogSecondColor.values().forEach { color ->
                    ColorOption(
                        id = color.id,
                        displayName = color.resourceName,
                        colors = color.colors,
                    )
                }
            }
            UserConfigurations(
                id = UserAnalogHandColor.ID,
                displayName = UserAnalogHandColor.displayName,
                defaultValue = Colors.White.id,
            ) {
                UserAnalogHandColor.values().forEach { color ->
                    ColorOption(
                        id = color.id,
                        displayName = color.resourceName,
                        colors = color.colors,
                    )
                }
            }
        }
        Scene {
            val secondCircleStrokeSize = (this@createWatchFace.width * 2f / 100f).roundToInt()
            Slots(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
            )
            SecondHand(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
                strokeSize = secondCircleStrokeSize,
            )
            AnalogClockNumber(
                width = this@createWatchFace.width,
                height =this@createWatchFace.height,
                margin = secondCircleStrokeSize,
            )
            HourMinHand(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
            )
            CenterDigitalClock(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
            )
            Scale(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
                maxStrokeSize = secondCircleStrokeSize,
            )
        }
    }
}
