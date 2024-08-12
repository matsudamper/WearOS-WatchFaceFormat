package net.matsudamper.watchface.color


enum class Colors {
    Red {
        override val id: String = "Red"
        override val resourceName: String = "color_red"
        override val color: String = "#E57373"
    },
    Pink {
        override val id: String = "Pink"
        override val resourceName: String = "color_pink"
        override val color: String = "#F06292"
    },
    Purple {
        override val id: String = "Purple"
        override val resourceName: String = "color_purple"
        override val color: String = "#BA68C8"
    },
    DeepPurple {
        override val id: String = "DeepPurple"
        override val resourceName: String = "color_deep_purple"
        override val color: String = "#7E57C2"
    },
    Indigo {
        override val id: String = "Indigo"
        override val resourceName: String = "color_indigo"
        override val color: String = "#7986CB"
    },
    Blue {
        override val id: String = "Blue"
        override val resourceName: String = "color_blue"
        override val color: String = "#64B5F6"
    },
    LightBlue {
        override val id: String = "LightBlue"
        override val resourceName: String = "color_light_blue"
        override val color: String = "#4FC3F7"
    },
    Cyan {
        override val id: String = "Cyan"
        override val resourceName: String = "color_cyan"
        override val color: String = "#4DD0E1"
    },
    Teal {
        override val id: String = "Teal"
        override val resourceName: String = "color_teal"
        override val color: String = "#4DB6AC"
    },
    Green {
        override val id: String = "Green"
        override val resourceName: String = "color_green"
        override val color: String = "#81C784"
    },
    LightGreen {
        override val id: String = "LightGreen"
        override val resourceName: String = "color_light_green"
        override val color: String = "#AED581"
    },
    Lime {
        override val id: String = "Lime"
        override val resourceName: String = "color_lime"
        override val color: String = "#DCE775"
    },
    Yellow {
        override val id: String = "Yellow"
        override val resourceName: String = "color_yellow"
        override val color: String = "#FFF176"
    },
    Amber {
        override val id: String = "Amber"
        override val resourceName: String = "color_amber"
        override val color: String = "#FFD54F"
    },
    Orange {
        override val id: String = "Orange"
        override val resourceName: String = "color_orange"
        override val color: String = "#FFB74D"
    },
    DeepOrange {
        override val id: String = "DeepOrange"
        override val resourceName: String = "color_deep_orange"
        override val color: String = "#FF8A65"
    },
    Brown {
        override val id: String = "Brown"
        override val resourceName: String = "color_brown"
        override val color: String = "#A1887F"
    },
    Grey {
        override val id: String = "Grey"
        override val resourceName: String = "color_grey"
        override val color: String = "#E0E0E0"
    },
    BlueGrey {
        override val id: String = "BlueGrey"
        override val resourceName: String = "color_blue_grey"
        override val color: String = "#90A4AE"
    },
    White {
        override val id: String = "White"
        override val resourceName: String = "color_white"
        override val color: String = "#FFFFFF"
    },
    ;

    abstract val id: String
    abstract val resourceName: String
    abstract val color: String
}
