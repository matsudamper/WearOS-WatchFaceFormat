package net.matsudamper.watchface

import net.matsudamper.watchface.dsl.element.VariantMode
import net.matsudamper.watchface.dsl.scope.PartImage
import net.matsudamper.watchface.dsl.scope.SceneScope
import net.matsudamper.watchface.dsl.scope.Variant

internal object PhotoBackground {
    const val CONFIGURATION_ID = "backgroundPhoto"

    // 黒背景の上に半透明で重ね、写真の上でも針や文字を読めるよう暗くする
    const val DIMMED_ALPHA = 128

    const val DEFAULT_IMAGE_RESOURCE = "photo_background_default"
}

internal fun SceneScope.PhotoBackground(
    width: Int,
    height: Int,
) {
    PartImage(
        name = "photoBackground",
        x = 0,
        y = 0,
        width = width,
        height = height,
        alpha = PhotoBackground.DIMMED_ALPHA,
    ) {
        Variant(
            mode = VariantMode.AMBIENT,
            target = "alpha",
            value = "0",
        )
        Photos(
            source = "[CONFIGURATION.${PhotoBackground.CONFIGURATION_ID}]",
            defaultImageResource = PhotoBackground.DEFAULT_IMAGE_RESOURCE,
            change = null,
            changeAfterEvery = null,
            width = null,
            height = null,
        )
    }
}
