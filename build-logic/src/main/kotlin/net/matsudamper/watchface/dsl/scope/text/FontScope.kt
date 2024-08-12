package net.matsudamper.watchface.dsl.scope.text

import net.matsudamper.watchface.dsl.element.FontFamily
import net.matsudamper.watchface.dsl.element.FontSlant
import net.matsudamper.watchface.dsl.element.FontWeight
import net.matsudamper.watchface.dsl.element.FontWidth
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
@Suppress("FunctionName")
class FontScope(
    val family: FontFamily?,
    val size: Int,
    val color: String?,
    val slant: FontSlant?,
    val letterSpacing: Float,
    val width: FontWidth?,
    val weight: FontWeight,
) : WatchFaceHasChildElement {
    override val elementName: String = "Font"
    override val attributes: Map<String, String?> = mapOf(
        "family" to family?.value,
        "size" to size.toString(),
        "color" to color,
        "slant" to slant?.value,
        "letterSpacing" to letterSpacing.toString(),
        "width" to width?.value,
        "weight" to weight.value,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun Template(
        block: TemplateScope.() -> Unit,
    ) {
        val scope = TemplateScope()
        scope.block()
        children.add(scope)
    }
}
