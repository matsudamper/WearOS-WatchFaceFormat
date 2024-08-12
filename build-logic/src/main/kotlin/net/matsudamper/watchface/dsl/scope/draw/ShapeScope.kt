package net.matsudamper.watchface.dsl.scope.draw

import net.matsudamper.watchface.dsl.element.Cap
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement

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