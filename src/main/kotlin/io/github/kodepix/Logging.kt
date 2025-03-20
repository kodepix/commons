package io.github.kodepix

import io.github.oshai.kotlinlogging.*


/**
 * This method allow defining the logger in a file by lazy function.
 * Logger name detected from caller file.
 *
 * Usage:
 *
 * ```kotlin
 * val log by logger()
 * ```
 *
 * @sample io.github.kodepix.samples.loggerSample
 */
fun logger() = lazy {
    KotlinLogging.logger(
        try {
            Thread.currentThread().stackTrace[4].className
        } catch (e: Throwable) {
            println(e)
            "logger"
        }
    )
}
