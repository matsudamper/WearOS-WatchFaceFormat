package net.matsudamper.dsl.element

enum class ComplicationSlotSupportedType(val value: String) {
    SHORT_TEXT("SHORT_TEXT"),
    LONG_TEXT("LONG_TEXT"),
    MONOCHROMATIC_IMAGE("MONOCHROMATIC_IMAGE"),
    SMALL_IMAGE("SMALL_IMAGE"),
    PHOTO_IMAGE("PHOTO_IMAGE"),
    RANGED_VALUE("RANGED_VALUE"),
    EMPTY("EMPTY")
}
