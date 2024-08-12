package net.matsudamper.watchface.dsl.scope.text

import net.matsudamper.watchface.dsl.element.FontFamily
import net.matsudamper.watchface.dsl.element.FontSlant
import net.matsudamper.watchface.dsl.element.FontWeight
import net.matsudamper.watchface.dsl.element.FontWidth
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement

/**
 * FOntを子に持てる
 */
interface HasFontElement: WatchFaceHasChildElement

@Suppress("FunctionName")
fun HasFontElement.Font(
    family: FontFamily = FontFamily.SyncToDevice,
    size: Int,
    color: String? = null,
    slant: FontSlant? = null,
    letterSpacing: Float = 0f,
    width: FontWidth? = null,
    weight: FontWeight = FontWeight.NORMAL,
    block: FontScope.() -> Unit = {},
) {
    addChild(
        FontScope(
            family = family,
            size = size,
            color = color,
            slant = slant,
            letterSpacing = letterSpacing,
            width = width,
            weight = weight,
        ).apply(block)
    )
}
