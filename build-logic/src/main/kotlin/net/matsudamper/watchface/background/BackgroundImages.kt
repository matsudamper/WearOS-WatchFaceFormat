package net.matsudamper.watchface.background

object BackgroundImages {
    const val CONFIGURATION_ID = "backgroundImage"
    const val DISPLAY_NAME = "background_image"

    val options: List<BackgroundOption> = listOf(
        BackgroundOption(id = "none", displayName = "background_none", drawableRes = null),
        BackgroundOption(id = "dark", displayName = "background_dark", drawableRes = "drawable/bg_dark"),
        BackgroundOption(id = "light", displayName = "background_light", drawableRes = "drawable/bg_light"),
    )
}

data class BackgroundOption(
    val id: String,
    val displayName: String,
    val drawableRes: String?,
)
