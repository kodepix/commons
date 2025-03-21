@file:Suppress("unused")

package io.github.kodepix

import java.io.*


/**
 * Creates a tuple of type [Triple] from values of this [Pair] and [that].
 */
infix fun <A, B, C> Pair<A, B>.and(that: C) = Triple(first, second, that)


/**
 * Creates a tuple of type [Quad] from values of this [Triple] and [that].
 */
infix fun <A, B, C, D> Triple<A, B, C>.also(that: D) = Quad(first, second, third, that)


/**
 * Represents a foursome of values.
 *
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * Quad exhibits value semantics, i.e. two quads are equal if all four components are equal.
 *
 * @param A type of the first value
 * @param B type of the second value
 * @param C type of the third value
 * @param D type of the fourth value
 * @property first First value
 * @property second Second value
 * @property third Third value
 * @property fourth Fourth value
 */
data class Quad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
) : Serializable {

    /**
     * Returns string representation of the [Quad] including its [first], [second], [third] and [fourth] values.
     */
    override fun toString() = "($first, $second, $third, $fourth)"
}
