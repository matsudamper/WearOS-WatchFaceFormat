package net.matsudamper.dsl.scope

import net.matsudamper.dsl.element.WatchFaceHasChildElement

/**
 * レイアウトに関する子を持てる
 * TODO一覧
 *         <Group ... />
 *         <PartImage ... />
 *         <PartAnimatedImage ... />
 *         <AnalogClock ... />
 */
interface HasWatchFaceLayoutElement : WatchFaceHasChildElement
