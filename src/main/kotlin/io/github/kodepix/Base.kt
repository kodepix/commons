@file:Suppress("unused")

package io.github.kodepix

import io.github.oshai.kotlinlogging.Level.*
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds


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
 * Executes [action] ignoring exceptions until success.
 *
 * Delayed 1 sec if exceptions occurs.
 *
 * Usage:
 *
 * ```kotlin
 * runUntilSuccess {
 *     // Database connecting, for example.
 * }
 * ```
 *
 * @param action action
 *
 * @sample io.github.kodepix.samples.runUntilSuccessSample
 */
fun runUntilSuccess(action: () -> Unit) = runBlocking {

    val log by statefulLogger(onceLogLevels = listOf(WARN), successLogLevel = INFO)
    val caller = Thread.currentThread().stackTrace[12].methodName

    while (isActive) {
        try {
            action()
            break
        } catch (e: Exception) {
            log.warn { "$caller: ${e.message}" }
            delay(1.seconds)
        }
    }
}


/**
 * [ByteArray] example. Useful for documentation purposes.
 */
val byteArrayExample get() = kotlin.random.Random.nextBytes(10)
