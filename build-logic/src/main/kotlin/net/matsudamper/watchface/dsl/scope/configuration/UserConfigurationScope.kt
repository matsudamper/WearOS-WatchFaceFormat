package net.matsudamper.watchface.dsl.scope.configuration

import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.element.WatchFaceElement

@Suppress("FunctionName")
class UserConfigurationScope : WatchFaceHasChildElement {
    override val elementName: String = "UserConfigurations"
    override val attributes: Map<String, String?> = mapOf()
    override val children:MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

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
