package net.matsudamper.watchface.dsl.scope.text

import net.matsudamper.watchface.dsl.element.FontFamily
import net.matsudamper.watchface.dsl.element.FontSlant
import net.matsudamper.watchface.dsl.element.FontWeight
import net.matsudamper.watchface.dsl.element.FontWidth
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

/**
 * FOntを子に持てる
 */
interface HasFontElement : WatchFaceHasChildElement

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

@Suppress("FunctionName")
fun HasFontElement.BitmapFont(
    family: FontFamily.Value,
    size: Int,
    color: String? = null,
    block: BitmapFontScope.() -> Unit = {},
) {
    addChild(
        BitmapFontScope(
            family = family,
            size = size,
            color = color,
        ).apply(block)
    )
}

@WatchFaceDSLMarker
class BitmapFontScope(
    val family: FontFamily,
    val size: Int,
    val color: String?,
) : WatchFaceHasChildElement, FontBitmapFontScope {
    override val elementName: String = "BitmapFont"
    override val attributes: Map<String, String?> = mapOf(
        "family" to family.value,
        "size" to size.toString(),
        "color" to color,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }
}
