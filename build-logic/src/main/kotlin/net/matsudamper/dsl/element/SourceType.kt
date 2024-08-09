package net.matsudamper.dsl.element

/**
 * https://developer.android.com/training/wearables/wff/common/attributes/source-type
 */
enum class SourceType(private val value: String) {
    SECOND_MILLISECOND("SECOND_MILLISECOND"),
    SECOND("SECOND"),
    ;

    val symbol: String = "[$value]"
}
