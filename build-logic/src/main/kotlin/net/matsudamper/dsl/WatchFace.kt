@file:Suppress("FunctionName")

package net.matsudamper.dsl

import net.matsudamper.dsl.element.ClipShape
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceScope

fun createWatchFace(
    clipShape: ClipShape,
    height: Int,
    width: Int,
    block: WatchFaceScope.() -> Unit,
): String {
    val scope = WatchFaceScope(
        clipShape = clipShape,
        height = height,
        width = width,
    )
    block(scope)
    return generateXml(scope)
}

private fun generateXml(element: WatchFaceElement): String {
    val attributes = element.attributes
        .mapNotNull { (key, value) ->
            value ?: return@mapNotNull null
            """$key="$value""""
        }

    return buildString {
        appendLine("<${element.elementName}")

        if (attributes.isNotEmpty()) {
            attributes.forEach {
                appendLine("$it")
            }
        }

        if (element.children.isEmpty()) {
            appendLine("/>")
        } else {
            appendLine(">")
            element.children.forEach {
                append(generateXml(it))
            }
            appendLine("</${element.elementName}>")
        }
    }
}
