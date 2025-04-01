package io.github.kodepix

import io.github.oshai.kotlinlogging.Level.*
import kotlinx.coroutines.*
import kotlin.time.*
import kotlin.time.Duration.Companion.seconds


/**
 * Executes [action] ignoring exceptions until success.
 *
 * Delayed [delay] if exceptions occurs.
 *
 * Usage:
 *
 * ```kotlin
 * runUntilSuccess {
 *     // Database connecting, for example.
 * }
 * ```
 *
 * @param delay delay if exceptions occurs
 * @param action action
 *
 * @sample io.github.kodepix.samples.runUntilSuccessSample
 */
fun runUntilSuccess(delay: Duration = 1.seconds, action: () -> Unit) = runBlocking {

    val log by statefulLogger(onceLogLevels = listOf(WARN), successLogLevel = INFO)
    val caller = Thread.currentThread().stackTrace[12].methodName

    while (isActive) {
        try {
            action()
            break
        } catch (e: Exception) {
            log.warn { "$caller: ${e.message}" }
            delay(delay)
        }
    }
}


/**
 * Executes [action] in a loop and then waits for [delay] after each execution.
 *
 * Usage:
 *
 * ```kotlin
 * runBlocking {
 *     doAndDelay(2.seconds) {
 *         // Periodical work.
 *     }
 * }
 * ```
 *
 * @param delay waiting time
 * @param action executing action
 *
 * @sample io.github.kodepix.samples.doAndDelaySample
 */
suspend fun CoroutineScope.doAndDelay(delay: Duration, action: suspend () -> Unit) {
    while (isActive) {
        action()
        delay(delay)
    }
}
