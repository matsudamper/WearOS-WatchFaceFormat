package net.matsudamper

enum class ContentColor {
    Red {
        override val id: String = "Red"
        override val resourceName: String = "color_red"
        override val colors: List<String> = listOf("#E57373")
    },
    Pink {
        override val id: String = "Pink"
        override val resourceName: String = "color_pink"
        override val colors: List<String> = listOf("#F06292")
    },
    Purple {
        override val id: String = "Purple"
        override val resourceName: String = "color_purple"
        override val colors: List<String> = listOf("#BA68C8")
    },
    DeepPurple {
        override val id: String = "DeepPurple"
        override val resourceName: String = "color_deep_purple"
        override val colors: List<String> = listOf("#7E57C2")
    },
    Indigo {
        override val id: String = "Indigo"
        override val resourceName: String = "color_indigo"
        override val colors: List<String> = listOf("#7986CB")
    },
    Blue {
        override val id: String = "Blue"
        override val resourceName: String = "color_blue"
        override val colors: List<String> = listOf("#64B5F6")
    },
    LightBlue {
        override val id: String = "LightBlue"
        override val resourceName: String = "color_light_blue"
        override val colors: List<String> = listOf("#4FC3F7")
    },
    Cyan {
        override val id: String = "Cyan"
        override val resourceName: String = "color_cyan"
        override val colors: List<String> = listOf("#4DD0E1")
    },
    Teal {
        override val id: String = "Teal"
        override val resourceName: String = "color_teal"
        override val colors: List<String> = listOf("#4DB6AC")
    },
    Green {
        override val id: String = "Green"
        override val resourceName: String = "color_green"
        override val colors: List<String> = listOf("#81C784")
    },
    LightGreen {
        override val id: String = "LightGreen"
        override val resourceName: String = "color_light_green"
        override val colors: List<String> = listOf("#AED581")
    },
    Lime {
        override val id: String = "Lime"
        override val resourceName: String = "color_lime"
        override val colors: List<String> = listOf("#DCE775")
    },
    Yellow {
        override val id: String = "Yellow"
        override val resourceName: String = "color_yellow"
        override val colors: List<String> = listOf("#FFF176")
    },
    Amber {
        override val id: String = "Amber"
        override val resourceName: String = "color_amber"
        override val colors: List<String> = listOf("#FFD54F")
    },
    Orange {
        override val id: String = "Orange"
        override val resourceName: String = "color_orange"
        override val colors: List<String> = listOf("#FFB74D")
    },
    DeepOrange {
        override val id: String = "DeepOrange"
        override val resourceName: String = "color_deep_orange"
        override val colors: List<String> = listOf("#FF8A65")
    },
    Brown {
        override val id: String = "Brown"
        override val resourceName: String = "color_brown"
        override val colors: List<String> = listOf("#A1887F")
    },
    Grey {
        override val id: String = "Grey"
        override val resourceName: String = "color_grey"
        override val colors: List<String> = listOf("#E0E0E0")
    },
    BlueGrey {
        override val id: String = "BlueGrey"
        override val resourceName: String = "color_blue_grey"
        override val colors: List<String> = listOf("#90A4AE")
    },
    ;

    abstract val id: String
    abstract val resourceName: String
    abstract val colors: List<String>

    companion object {
        const val ID = "contentColor"

        fun getColorSymbol(): String {
            return "[CONFIGURATION.$ID.0]"
        }
    }
}
