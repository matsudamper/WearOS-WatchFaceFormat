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
import net.matsudamper.watchface.color.UserScaleColor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import kotlin.math.roundToInt

class WatchFaceFormatGeneratorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val generateWatchFace = tasks.register("generateWatchFace", GenerateWatchFaceTask::class.java) {
                outputFile.set(layout.projectDirectory.file("src/main/res/raw/watchface.xml"))
            }

            val watchFaceValidatorDependencies = configurations.dependencyScope("watchFaceValidator")
            val watchFaceValidatorClasspath = configurations.resolvable("watchFaceValidatorClasspath") {
                extendsFrom(watchFaceValidatorDependencies.get())
            }
            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
            dependencies.addProvider(
                watchFaceValidatorDependencies.name,
                dependencies.variantOf(libs.findLibrary("xercesXsd11").get()) { classifier("xml-schema-1.1") },
            )
            // XSD 1.1のassert評価に必要だが、xercesのPOMではoptionalのため明示する
            dependencies.addProvider(watchFaceValidatorDependencies.name, libs.findLibrary("xpath2").get())
            dependencies.addProvider(watchFaceValidatorDependencies.name, libs.findLibrary("javaCup").get())

            val validateWatchFace = tasks.register("validateWatchFace", ValidateWatchFaceTask::class.java) {
                watchFaceFile.set(generateWatchFace.flatMap { it.outputFile })
                manifestFile.set(layout.projectDirectory.file("src/main/AndroidManifest.xml"))
                specificationDirectory.set(rootProject.layout.projectDirectory.dir("third_party/wff-specification/documents"))
                validatorClasspath.from(watchFaceValidatorClasspath)
                reportFile.set(layout.buildDirectory.file("intermediates/watchface/validateWatchFace.txt"))
            }

            tasks.named("preBuild") {
                dependsOn(generateWatchFace)
                dependsOn(validateWatchFace)
            }
        }
    }
}

internal fun generate(): String {
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
            UserConfigurations(
                id = UserScaleColor.ID,
                displayName = UserScaleColor.displayName,
                defaultValue = Colors.White.id,
            ) {
                UserScaleColor.values().forEach { color ->
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
            SecondHand(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
                strokeSize = secondCircleStrokeSize,
            )
            Scale(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
                maxStrokeSize = secondCircleStrokeSize,
            )
            AnalogClockNumber(
                width = this@createWatchFace.width,
                height =this@createWatchFace.height,
                margin = secondCircleStrokeSize,
            )
            Slots(
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
