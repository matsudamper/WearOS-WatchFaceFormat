@file:Suppress("FunctionName")

package net.matsudamper.dsl

import net.matsudamper.dsl.element.ClipShape
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.element.WatchFaceTextElement
import net.matsudamper.dsl.scope.WatchFaceHasChildScope

fun createWatchFace(
    clipShape: ClipShape,
    height: Int,
    width: Int,
    block: WatchFaceHasChildScope.() -> Unit,
): String {
    val scope = WatchFaceHasChildScope(
        clipShape = clipShape,
        height = height,
        width = width,
    )
    block(scope)
    return buildString {
        appendLine("""<?xml version="1.0"?>""")
        append(generateXml(scope))
    }
}

private fun generateXml(element: WatchFaceElement): String {
    when (element) {
        is WatchFaceHasChildElement -> Unit
        is WatchFaceTextElement -> {
            return element.text + "\n"
        }
    }

    val attributes = element.attributes
        .mapNotNull { (key, value) ->
            value ?: return@mapNotNull null
            """$key="$value""""
        }

    return buildString {
        append("<${element.elementName}")
        if (attributes.isNotEmpty()) {
            appendLine()
            for ((index, item) in attributes.withIndex()) {
                append("    $item")
                if (index != attributes.size - 1) {
                    appendLine()
                }
            }
        }

        if (element.children.isEmpty()) {
            appendLine(" />")
        } else {
            if (attributes.isEmpty()) {
                appendLine(">")
            } else {
                appendLine(" >")
            }
            element.children.forEach { child ->
                append(
                    generateXml(child)
                        .split("\n")
                        .joinToString("\n") {
                            if (it.isBlank()) {
                                it
                            } else {
                                "    $it"
                            }
                        }
                )
            }
            appendLine("</${element.elementName}>")
        }
    }
}
