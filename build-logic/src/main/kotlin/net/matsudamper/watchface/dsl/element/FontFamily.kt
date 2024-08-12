package net.matsudamper.watchface.dsl.element

sealed interface FontFamily {
    val value: String

    object SyncToDevice : FontFamily {
        override val value: String = "SYNC_TO_DEVICE"
    }

    data class Value(override val value: String) : FontFamily
}
