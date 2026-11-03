package net.matsudamper.watchface

import net.matsudamper.watchface.dsl.element.Interpolation
import net.matsudamper.watchface.dsl.element.VariantTransition

internal val AmbientFadeTransition = VariantTransition(
    duration = 1f,
    startOffset = 0f,
    interpolation = Interpolation.EASE_IN_OUT,
)
