package net.matsudamper.watchface.dsl.element

enum class AnimationPlayEvent(val value: String) {
    TAP("TAP"),
    ON_VISIBLE("ON_VISIBLE"),
    ON_NEXT_SECOND("ON_NEXT_SECOND"),
    ON_NEXT_MINUTE("ON_NEXT_MINUTE"),
    ON_NEXT_HOUR("ON_NEXT_HOUR"),
}
