package net.matsudamper.watchface.color

import net.matsudamper.watchface.dsl.scope.configuration.ColorOptionId


enum class Colors {
    Red {
        override val id: ColorOptionId = ColorOptionId(0)
        override val resourceName: String = "color_red"
        override val color: String = "#E57373"
    },
    Pink {
        override val id: ColorOptionId = ColorOptionId(1)
        override val resourceName: String = "color_pink"
        override val color: String = "#F06292"
    },
    Purple {
        override val id: ColorOptionId = ColorOptionId(2)
        override val resourceName: String = "color_purple"
        override val color: String = "#BA68C8"
    },
    DeepPurple {
        override val id: ColorOptionId = ColorOptionId(3)
        override val resourceName: String = "color_deep_purple"
        override val color: String = "#7E57C2"
    },
    Indigo {
        override val id: ColorOptionId = ColorOptionId(4)
        override val resourceName: String = "color_indigo"
        override val color: String = "#7986CB"
    },
    Blue {
        override val id: ColorOptionId = ColorOptionId(5)
        override val resourceName: String = "color_blue"
        override val color: String = "#64B5F6"
    },
    LightBlue {
        override val id: ColorOptionId = ColorOptionId(6)
        override val resourceName: String = "color_light_blue"
        override val color: String = "#4FC3F7"
    },
    Cyan {
        override val id: ColorOptionId = ColorOptionId(7)
        override val resourceName: String = "color_cyan"
        override val color: String = "#4DD0E1"
    },
    Teal {
        override val id: ColorOptionId = ColorOptionId(8)
        override val resourceName: String = "color_teal"
        override val color: String = "#4DB6AC"
    },
    Green {
        override val id: ColorOptionId = ColorOptionId(9)
        override val resourceName: String = "color_green"
        override val color: String = "#81C784"
    },
    LightGreen {
        override val id: ColorOptionId = ColorOptionId(10)
        override val resourceName: String = "color_light_green"
        override val color: String = "#AED581"
    },
    Lime {
        override val id: ColorOptionId = ColorOptionId(11)
        override val resourceName: String = "color_lime"
        override val color: String = "#DCE775"
    },
    Yellow {
        override val id: ColorOptionId = ColorOptionId(12)
        override val resourceName: String = "color_yellow"
        override val color: String = "#FFF176"
    },
    Amber {
        override val id: ColorOptionId = ColorOptionId(13)
        override val resourceName: String = "color_amber"
        override val color: String = "#FFD54F"
    },
    Orange {
        override val id: ColorOptionId = ColorOptionId(14)
        override val resourceName: String = "color_orange"
        override val color: String = "#FFB74D"
    },
    DeepOrange {
        override val id: ColorOptionId = ColorOptionId(15)
        override val resourceName: String = "color_deep_orange"
        override val color: String = "#FF8A65"
    },
    Brown {
        override val id: ColorOptionId = ColorOptionId(16)
        override val resourceName: String = "color_brown"
        override val color: String = "#A1887F"
    },
    Grey {
        override val id: ColorOptionId = ColorOptionId(17)
        override val resourceName: String = "color_grey"
        override val color: String = "#E0E0E0"
    },
    BlueGrey {
        override val id: ColorOptionId = ColorOptionId(18)
        override val resourceName: String = "color_blue_grey"
        override val color: String = "#90A4AE"
    },
    White {
        override val id: ColorOptionId = ColorOptionId(19)
        override val resourceName: String = "color_white"
        override val color: String = "#FFFFFF"
    },
    ;

    abstract val id: ColorOptionId
    abstract val resourceName: String
    abstract val color: String
}
