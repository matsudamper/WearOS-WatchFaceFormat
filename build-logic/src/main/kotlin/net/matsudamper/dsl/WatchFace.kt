@file:Suppress("FunctionName")

package net.matsudamper.dsl

import net.matsudamper.dsl.element.ClipShape
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
    return scope.getXml()
}
