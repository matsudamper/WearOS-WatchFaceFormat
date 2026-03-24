package net.matsudamper.watchface.dsl.scope.animatedimage

import net.matsudamper.watchface.dsl.element.AnimatedImageFormat
import net.matsudamper.watchface.dsl.element.AnimationChangeDirection
import net.matsudamper.watchface.dsl.element.AnimationPlayEvent
import net.matsudamper.watchface.dsl.element.RenderMode
import net.matsudamper.watchface.dsl.element.WatchFaceElement
import net.matsudamper.watchface.dsl.element.WatchFaceHasChildElement
import net.matsudamper.watchface.dsl.scope.WatchFaceDSLMarker

@WatchFaceDSLMarker
class PartAnimatedImageScope(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val name: String?,
    val angle: Float?,
    val pivotX: Float?,
    val pivotY: Float?,
    val alpha: Int?,
    val scaleX: Float?,
    val scaleY: Float?,
    val renderMode: RenderMode?,
    val tintColor: String?,
    val blendMode: String?,
) : WatchFaceHasChildElement {
    override val elementName: String = "PartAnimatedImage"
    override val attributes: Map<String, String?> = mapOf(
        "x" to x.toString(),
        "y" to y.toString(),
        "width" to width.toString(),
        "height" to height.toString(),
        "name" to name,
        "angle" to angle?.toString(),
        "pivotX" to pivotX?.toString(),
        "pivotY" to pivotY?.toString(),
        "alpha" to alpha?.toString(),
        "scaleX" to scaleX?.toString(),
        "scaleY" to scaleY?.toString(),
        "renderMode" to renderMode?.value,
        "tintColor" to tintColor,
        "blendMode" to blendMode,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun AnimationController(
        play: AnimationPlayEvent,
        delayPlay: Float? = null,
        delayRepeat: Float? = null,
        repeat: Boolean? = null,
        loopCount: Int? = null,
        resumePlayBack: Boolean? = null,
        beforePlaying: String? = null,
        afterPlaying: String? = null,
    ) {
        children.add(
            AnimationControllerScope(
                play = play,
                delayPlay = delayPlay,
                delayRepeat = delayRepeat,
                repeat = repeat,
                loopCount = loopCount,
                resumePlayBack = resumePlayBack,
                beforePlaying = beforePlaying,
                afterPlaying = afterPlaying,
            )
        )
    }

    fun AnimatedImage(
        resource: String,
        format: AnimatedImageFormat,
        thumbnail: String? = null,
    ) {
        children.add(
            AnimatedImageScope(
                resource = resource,
                format = format,
                thumbnail = thumbnail,
            )
        )
    }

    fun AnimatedImages(
        change: AnimationPlayEvent? = null,
        changeDirection: AnimationChangeDirection? = null,
        block: AnimatedImagesScope.() -> Unit,
    ) {
        children.add(AnimatedImagesScope(change = change, changeDirection = changeDirection).apply(block))
    }

    fun SequenceImage(
        loopCount: Int? = null,
        frameRate: Int? = null,
        thumbnail: String? = null,
        block: SequenceImageScope.() -> Unit,
    ) {
        children.add(
            SequenceImageScope(
                loopCount = loopCount,
                frameRate = frameRate,
                thumbnail = thumbnail,
            ).apply(block)
        )
    }

    fun Thumbnail(resource: String) {
        children.add(ThumbnailScope(resource = resource))
    }
}

class AnimationControllerScope(
    play: AnimationPlayEvent,
    delayPlay: Float?,
    delayRepeat: Float?,
    repeat: Boolean?,
    loopCount: Int?,
    resumePlayBack: Boolean?,
    beforePlaying: String?,
    afterPlaying: String?,
) : WatchFaceHasChildElement {
    override val elementName: String = "AnimationController"
    override val attributes: Map<String, String?> = mapOf(
        "play" to play.value,
        "delayPlay" to delayPlay?.toString(),
        "delayRepeat" to delayRepeat?.toString(),
        "repeat" to repeat?.toString()?.uppercase(),
        "loopCount" to loopCount?.toString(),
        "resumePlayBack" to resumePlayBack?.toString()?.uppercase(),
        "beforePlaying" to beforePlaying,
        "afterPlaying" to afterPlaying,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}

class AnimatedImageScope(
    resource: String,
    format: AnimatedImageFormat,
    thumbnail: String?,
) : WatchFaceHasChildElement {
    override val elementName: String = "AnimatedImage"
    override val attributes: Map<String, String?> = mapOf(
        "resource" to resource,
        "format" to format.value,
        "thumbnail" to thumbnail,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}

@WatchFaceDSLMarker
class AnimatedImagesScope(
    change: AnimationPlayEvent?,
    changeDirection: AnimationChangeDirection?,
) : WatchFaceHasChildElement {
    override val elementName: String = "AnimatedImages"
    override val attributes: Map<String, String?> = mapOf(
        "change" to change?.value,
        "changeDirection" to changeDirection?.value,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun AnimatedImage(
        resource: String,
        format: AnimatedImageFormat,
        thumbnail: String? = null,
    ) {
        children.add(
            AnimatedImageScope(
                resource = resource,
                format = format,
                thumbnail = thumbnail,
            )
        )
    }

    fun SequenceImage(
        loopCount: Int? = null,
        frameRate: Int? = null,
        thumbnail: String? = null,
        block: SequenceImageScope.() -> Unit,
    ) {
        children.add(
            SequenceImageScope(
                loopCount = loopCount,
                frameRate = frameRate,
                thumbnail = thumbnail,
            ).apply(block)
        )
    }
}

@WatchFaceDSLMarker
class SequenceImageScope(
    loopCount: Int?,
    frameRate: Int?,
    thumbnail: String?,
) : WatchFaceHasChildElement {
    override val elementName: String = "SequenceImage"
    override val attributes: Map<String, String?> = mapOf(
        "loopCount" to loopCount?.toString(),
        "frameRate" to frameRate?.toString(),
        "thumbnail" to thumbnail,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        children.add(child)
    }

    fun Image(resource: String) {
        children.add(SequenceFrameScope(resource = resource))
    }
}

class SequenceFrameScope(resource: String) : WatchFaceHasChildElement {
    override val elementName: String = "Image"
    override val attributes: Map<String, String?> = mapOf(
        "resource" to resource,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}

class ThumbnailScope(resource: String) : WatchFaceHasChildElement {
    override val elementName: String = "Thumbnail"
    override val attributes: Map<String, String?> = mapOf(
        "resource" to resource,
    )
    override val children: MutableList<WatchFaceElement> = mutableListOf()
    override fun addChild(child: WatchFaceElement) {
        throw UnsupportedOperationException()
    }
}
