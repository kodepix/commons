@file:Suppress("unused")

package io.github.kodepix


/**
 * Int based identifier.
 *
 * Usage:
 *
 * ```kotlin
 * val id = Id(123)
 * ```
 *
 * @property value identifier's value
 *
 * @sample io.github.kodepix.samples.idSample
 */
@JvmInline
value class Id(val value: Int) {
    override fun toString() = value.toString()

    companion object {
        val example = Id(789)
    }
}


/**
 * Explicitly cast an object. Useful to avoid "Unchecked cast".
 *
 * Usage:
 *
 * ```kotlin
 * val any: Any = "string"
 * val string = any.cast<String>()
 * ```
 *
 * @param T class to cast
 *
 * @sample io.github.kodepix.samples.castSample
 */
inline fun <reified T> Any?.cast(): T = this as T


/**
 * [ByteArray] example. Useful for documentation purposes.
 */
val byteArrayExample get() = kotlin.random.Random.nextBytes(10)
