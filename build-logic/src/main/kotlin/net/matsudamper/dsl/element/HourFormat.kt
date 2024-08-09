package net.matsudamper.dsl.element

enum class HourFormat(val value: String) {
    H12("12"),
    H24("24"),
    SYNC_TO_DEVICE("SYNC_TO_DEVICE"),
}