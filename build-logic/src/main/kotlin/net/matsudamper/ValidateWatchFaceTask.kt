package net.matsudamper

import javax.inject.Inject
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.gradle.workers.WorkerExecutor
import org.xml.sax.ErrorHandler
import org.xml.sax.SAXParseException

abstract class ValidateWatchFaceTask @Inject constructor(
    private val workerExecutor: WorkerExecutor,
) : DefaultTask() {
    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val watchFaceFile: RegularFileProperty

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val manifestFile: RegularFileProperty

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val specificationDirectory: DirectoryProperty

    @get:Classpath
    abstract val validatorClasspath: ConfigurableFileCollection

    @get:OutputFile
    abstract val reportFile: RegularFileProperty

    @TaskAction
    fun validate() {
        val formatVersion = readWatchFaceFormatVersion()
        val schemaFile = specificationDirectory.file("$formatVersion/watchface.xsd").get().asFile
        if (!schemaFile.exists()) {
            throw GradleException("Watch Face Format version $formatVersion の XSD がありません: $schemaFile")
        }
        // xercesのMETA-INF/servicesがAGPのJAXP解決に混ざらないよう、隔離したクラスローダーで検証する
        workerExecutor.classLoaderIsolation { classpath.from(validatorClasspath) }
            .submit(ValidateWatchFaceAction::class.java) {
                watchFace.set(watchFaceFile)
                schema.set(schemaFile)
                report.set(reportFile)
            }
    }

    private fun readWatchFaceFormatVersion(): String {
        val documentBuilderFactory = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = true
        }
        val properties = documentBuilderFactory.newDocumentBuilder()
            .parse(manifestFile.get().asFile)
            .getElementsByTagName("property")
        val formatVersion = (0 until properties.length)
            .map { properties.item(it) as org.w3c.dom.Element }
            .firstOrNull { it.getAttributeNS(ANDROID_NAMESPACE, "name") == WATCH_FACE_FORMAT_VERSION_PROPERTY }
            ?.getAttributeNS(ANDROID_NAMESPACE, "value")
        if (formatVersion.isNullOrEmpty()) {
            throw GradleException("AndroidManifest.xml に $WATCH_FACE_FORMAT_VERSION_PROPERTY がありません")
        }
        return formatVersion
    }

    private companion object {
        const val ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android"
        const val WATCH_FACE_FORMAT_VERSION_PROPERTY = "com.google.wear.watchface.format.version"
    }
}

interface ValidateWatchFaceParameters : WorkParameters {
    val watchFace: RegularFileProperty
    val schema: RegularFileProperty
    val report: RegularFileProperty
}

abstract class ValidateWatchFaceAction : WorkAction<ValidateWatchFaceParameters> {
    override fun execute() {
        val schemaFactory = SchemaFactory.newInstance(
            XSD_1_1_NAMESPACE,
            XERCES_XSD_1_1_FACTORY,
            javaClass.classLoader,
        )
        val validator = schemaFactory.newSchema(parameters.schema.get().asFile).newValidator()
        val watchFaceFile = parameters.watchFace.get().asFile
        val errors = buildList {
            validator.errorHandler = object : ErrorHandler {
                override fun warning(exception: SAXParseException) = Unit
                override fun error(exception: SAXParseException) {
                    add(exception.toMessage())
                }

                override fun fatalError(exception: SAXParseException) {
                    add(exception.toMessage())
                }
            }
            validator.validate(StreamSource(watchFaceFile))
        }
        if (errors.isNotEmpty()) {
            throw GradleException(
                buildString {
                    appendLine("$watchFaceFile が Watch Face Format の XSD に違反しています")
                    errors.forEach { appendLine(it) }
                },
            )
        }
        parameters.report.get().asFile.writeText("OK\n")
    }

    private fun SAXParseException.toMessage(): String {
        return "[Line $lineNumber:Column $columnNumber] $message"
    }

    private companion object {
        // WFFのXSDはxs:all内でmaxOccurs="unbounded"を使うXSD 1.1のため、JDK標準(XSD 1.0)ではなくxercesの1.1実装を使う
        const val XSD_1_1_NAMESPACE = "http://www.w3.org/XML/XMLSchema/v1.1"
        const val XERCES_XSD_1_1_FACTORY = "org.apache.xerces.jaxp.validation.XMLSchema11Factory"
    }
}
