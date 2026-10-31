package net.matsudamper.watchface.dsl.scope.configuration

import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@Suppress("FunctionName")
@WatchFaceDSLMarker
class FlavorsScope(
    val defaultValue: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Flavors"
    override val attributes: Map<String, String?> = mapOf(
        "defaultValue" to defaultValue,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun Flavor(
        id: String,
        displayName: String,
        block: FlavorScope.() -> Unit,
    ) {
        val scope = FlavorScope(
            id = id,
            displayName = displayName,
        )
        block(scope)
        children.add(scope)
    }
}

@Suppress("FunctionName")
@WatchFaceDSLMarker
class FlavorScope(
    val id: String,
    val displayName: String,
) : WatchFaceHasChildElement {
    override val elementName: String = "Flavor"
    override val attributes: Map<String, String?> = mapOf(
        "id" to id,
        "displayName" to displayName,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun Configuration(
        id: String,
        optionId: String,
    ) {
        children.add(
            FlavorConfiguration(
                id = id,
                optionId = optionId,
            )
        )
    }

    class FlavorConfiguration(
        id: String,
        optionId: String,
    ) : WatchFaceHasChildElement {
        override val elementName: String = "Configuration"
        override val attributes: Map<String, String?> = mapOf(
            "id" to id,
            "optionId" to optionId,
        )
        override val children: MutableList<WatchFaceElement> = mutableListOf()
        override fun addChild(child: WatchFaceElement) {
            throw UnsupportedOperationException()
        }
    }
}
