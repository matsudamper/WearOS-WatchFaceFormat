package net.matsudamper

import net.matsudamper.watchface.dsl.createWatchFace
import net.matsudamper.watchface.dsl.element.ClipShape
import net.matsudamper.watchface.dsl.metadata.ClockType
import net.matsudamper.watchface.dsl.metadata.ClockTypeValue
import net.matsudamper.watchface.CenterDigitalClock
import net.matsudamper.watchface.ContentColor
import net.matsudamper.watchface.HourMinHand
import net.matsudamper.watchface.SecondHand
import net.matsudamper.watchface.Slots
import org.gradle.api.Plugin
import org.gradle.api.Project

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
