package net.matsudamper

import net.matsudamper.watchface.color.ColorFlavor
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class GenerateWatchFaceTask : DefaultTask() {
    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Input
    abstract val defaultColorFlavorId: Property<String>

    @TaskAction
    fun generateXml() {
        val flavorId = defaultColorFlavorId.get()
        val defaultColorFlavor = ColorFlavor.entries.firstOrNull { it.id == flavorId }
            ?: throw IllegalArgumentException(
                "Unknown color flavor: $flavorId. Available: ${ColorFlavor.entries.joinToString { it.id }}",
            )
        outputFile.get().asFile.writeText(generate(defaultColorFlavor = defaultColorFlavor))
    }
}
