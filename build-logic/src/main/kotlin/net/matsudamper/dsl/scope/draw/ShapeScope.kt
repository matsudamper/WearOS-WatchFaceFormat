package net.matsudamper.dsl.scope.draw

import net.matsudamper.dsl.element.Cap
import net.matsudamper.dsl.element.WatchFaceHasChildElement

interface ShapeScope : WatchFaceHasChildElement


@Suppress("FunctionName")
fun WatchFaceHasChildElement.Transform(
    target: String,
    value: String,
) {
    addChild(
        TransformScope(
            target = target,
            value = value,
        )
    )
}

@Suppress("FunctionName")
fun ShapeScope.Stroke(
    cap: Cap,
    color: String,
    thickness: Int,
) {
    addChild(
        StrokeScope(
            cap = cap,
            color = color,
            thickness = thickness,
        )
    )
}