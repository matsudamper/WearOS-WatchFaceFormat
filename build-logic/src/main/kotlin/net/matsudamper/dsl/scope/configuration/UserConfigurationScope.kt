package net.matsudamper.dsl.scope.configuration

import net.matsudamper.dsl.element.WatchFaceHasChildElement
import net.matsudamper.dsl.element.WatchFaceElement

@Suppress("FunctionName")
class UserConfigurationScope : WatchFaceHasChildElement {
    override val elementName: String = "UserConfigurations"
    override val attributes: Map<String, String?> = mapOf()
    override val children:MutableList<WatchFaceElement> = mutableListOf()

    fun UserConfigurations(
        id: String? = null,
        displayName: String,
        icon: String? = null,
        screenReaderText: String? = null,
        defaultValue: String,
        block: ColorConfigurationScope.() -> Unit
    ) {
        val scope = ColorConfigurationScope(
            id = id,
            displayName = displayName,
            icon = icon,
            screenReaderText = screenReaderText,
            defaultValue = defaultValue
        )
        block(scope)
        children.add(scope)
    }
}
