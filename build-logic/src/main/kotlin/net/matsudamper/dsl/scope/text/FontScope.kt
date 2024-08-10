package net.matsudamper.dsl.scope.text

import net.matsudamper.dsl.element.FontFamily
import net.matsudamper.dsl.element.FontSlant
import net.matsudamper.dsl.element.FontWeight
import net.matsudamper.dsl.element.FontWidth
import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

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