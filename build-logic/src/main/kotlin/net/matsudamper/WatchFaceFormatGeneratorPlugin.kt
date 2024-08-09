package net.matsudamper

import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.ClipShape
import net.matsudamper.dsl.scope.SceneScope
import net.matsudamper.dsl.element.SourceType
import net.matsudamper.dsl.createWatchFace
import net.matsudamper.dsl.metadata.ClockType
import net.matsudamper.dsl.metadata.ClockTypeValue
import org.gradle.api.Plugin
import org.gradle.api.Project

class WatchFaceFormatGeneratorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val generateFormat = tasks.register("generateFormat") {
                doLast {
                    val xml = generate()
                    layout.projectDirectory.asFile.resolve("src/main/res/raw/watchface.xml")
                        .writeText(xml)
                }
            }

            tasks.named("preBuild") {
                dependsOn(generateFormat)
            }
        }
    }
}


enum class ContentColor {
    Red {
        override val id: String = "color_red"
        override val colors: List<String> = listOf("#E57373")
    },
    Pink {
        override val id: String = "color_pink"
        override val colors: List<String> = listOf("#F06292")
    },
    Green {
        override val id: String = "color_green"
        override val colors: List<String> = listOf("#66BB6A")
    }
    ;

    abstract val id: String
    abstract val colors: List<String>

    companion object {
        const val ID = "contentColor"

        fun getColorSymbol(): String {
            return "[CONFIGURATION.$ID.0]"
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
        UserConfiguration {
            UserConfigurations(
                id = ContentColor.ID,
                displayName = "content",
                defaultValue = ContentColor.Red.id,
            ) {
                ContentColor.values().forEach { color ->
                    ColorOption(
                        id = color.id,
                        displayName = color.id,
                        colors = color.colors,
                    )
                }
            }
        }
        Scene {
            SecondHand(
                width = this@createWatchFace.width,
                height = this@createWatchFace.height,
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
