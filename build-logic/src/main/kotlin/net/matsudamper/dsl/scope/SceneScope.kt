package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.RenderMode
import net.matsudamper.dsl.element.WatchFaceElement
import net.matsudamper.dsl.scope.clock.DigitalClockScope

@WatchFaceDSLMarker
@Suppress("FunctionName")
class SceneScope(
    val backgroundColor: String?,
) : WatchFaceElement {
    override val elementName: String = "Scene"
    override val attributes: Map<String, String?> = mapOf(
        "backgroundColor" to backgroundColor,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    fun DigitalClock(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        pivotX: Float? = null,
        pivotY: Float? = null,
        angle: Float? = null,
        alpha: Int = 255,
        scaleX: Float? = null,
        scaleY: Float? = null,
        renderMode: RenderMode? = null,
        tintColor: String? = null,
        block: DigitalClockScope.() -> Unit,
    ) {
        val scope = DigitalClockScope(
            x = x,
            y = y,
            width = width,
            height = height,
            pivotX = pivotX,
            pivotY = pivotY,
            angle = angle,
            alpha = alpha,
            scaleX = scaleX,
            scaleY = scaleY,
            renderMode = renderMode,
            tintColor = tintColor,
        )
        block(scope)
        children.add(scope)
    }

    fun PartDraw(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        name: String? = null,
        angle: Float = 0f,
        pivotX: Float = 0.5f,
        pivotY: Float = 0.5f,
        alpha: Int = 255,
        renderMode: RenderMode = RenderMode.SOURCE,
        tintColor: String? = null,
        block: PartDrawScope.() -> Unit,
    ) {
        val scope = PartDrawScope(
            x = x,
            y = y,
            width = width,
            height = height,
            name = name,
            angle = angle,
            pivotX = pivotX,
            pivotY = pivotY,
            alpha = alpha,
            renderMode = renderMode,
            tintColor = tintColor,
        )
        block(scope)
        children.add(scope)
    }
}
