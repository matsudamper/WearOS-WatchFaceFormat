package net.matsudamper.dsl.scope

fun Pair<String, String?>.toXmlAttribute(): String {
    second ?: return ""
    return """$first="$second""""
}
