package net.matsudamper.watchface.dsl.scope.configuration

/**
 * 数字以外の ID だと、スマホのアプリで Flavor の Configuration が適用されない
 */
data class ColorOptionId(
    val value: Int,
) {
    init {
        require(value >= 0) { "ColorOptionId must be non-negative: $value" }
    }

    val attributeValue: String get() = value.toString()
}
