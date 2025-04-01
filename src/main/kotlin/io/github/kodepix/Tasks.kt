package io.github.kodepix

import kotlinx.coroutines.*
import kotlin.time.*
import kotlin.time.Duration.Companion.INFINITE
import kotlin.time.Duration.Companion.milliseconds


/**
 * Start executing [block] in background.
 *
 * When an exception occurs in [block], restarts execution after delay [restartDelay].
 *
 * Usage:
 *
 * ```kotlin
 * runBlocking {
 *     launchBackground {
 *         // Background work.
 *     }
 * }
 * ```
 *
 * @param restartDelay restart delay after exception
 * @param block executing block
 *
 * @sample io.github.kodepix.samples.launchBackgroundSample
 */
fun CoroutineScope.launchBackground(restartDelay: Duration = 500.milliseconds, block: suspend CoroutineScope.() -> Unit) =
    launchBackground(Thread.currentThread().stackTrace[2].methodName, restartDelay, block)

private fun CoroutineScope.launchBackground(methodName: String, restartDelay: Duration, block: suspend CoroutineScope.() -> Unit) {

    log.info { "Task launched: $methodName (background)" }

    launch {
        while (isActive)
            supervisorScope {

                try {
                    block()
                    delay(INFINITE)
                } catch (_: CancellationException) {
                } catch (e: NullPointerException) {
                    log.error(e) { "Error in $methodName. Restarting after $restartDelay." }
                } catch (e: Exception) {
                    log.warn { "Error in $methodName: $e; cause: ${e.cause?.toString()}. Restarting after $restartDelay." }
                    delay(restartDelay)
                }
            }
    }
}


private val log by logger()
