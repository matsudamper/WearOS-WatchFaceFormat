package net.matsudamper.dsl.scope.text

import net.matsudamper.dsl.element.TextAlign
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.HasWatchFaceLayoutElement
import net.matsudamper.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
@Suppress("FunctionName")
class TextScope(
    val align: TextAlign?,
    val ellipsis: Boolean,
    val maxLines: Int?,
) : HasWatchFaceLayoutElement, HasFontElement {
    override val elementName: String = "Text"
    override val attributes: Map<String, String?> = mapOf(
        "align" to align?.value,
        "ellipsis" to ellipsis.toString(),
        "maxLines" to maxLines?.toString(),
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    /**
     * TODO
     *     Shadow
     *     Outline
     *     OutGlow
     *     Underline
     *     StrikeThrough
     *     InlineImage
     *     Upper
     *     Lower
     */

    fun Template(
        block: TemplateScope.() -> Unit
    ) {
        val template = TemplateScope()
        template.block()
        children.add(template)
    }
}
