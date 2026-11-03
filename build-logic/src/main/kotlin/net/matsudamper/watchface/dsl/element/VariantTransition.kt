package net.matsudamper.watchface.dsl.element

/**
 * durationとstartOffsetは、端末ごとに異なるambient遷移時間に対する比率(0.0〜1.0)
 */
data class VariantTransition(
    val duration: Float,
    val startOffset: Float,
    val interpolation: Interpolation,
)
