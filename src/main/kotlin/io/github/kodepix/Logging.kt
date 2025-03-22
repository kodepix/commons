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


/**
 * This method allow defining the [StatefulLogger] in a file by lazy function.
 * Logger name detected from caller file.
 *
 * Usage:
 *
 * ```kotlin
 * val log by statefulLogger(onceLogLevels = listOf(ERROR, WARN), successLogLevel = INFO)
 * ```
 *
 * @sample io.github.kodepix.samples.statefulLoggerSample
 */
fun statefulLogger(onceLogLevels: List<Level>, successLogLevel: Level) = lazy {
    StatefulLogger(
        name = try {
            Thread.currentThread().stackTrace[4].className
        } catch (e: Throwable) {
            println(e)
            "logger"
        },
        onceLogLevels = onceLogLevels,
        successLogLevel = successLogLevel
    )
}


/**
 * Defining the logger that displays the message only once if the logging level is set to [onceLogLevels].
 *
 * [successLogLevel] sets the logging level of the final message.
 *
 * @property name logger name
 * @property onceLogLevels logging levels
 * @property successLogLevel level of the final message logging
 */
class StatefulLogger(
    override val name: String,
    private val onceLogLevels: List<Level>,
    private val successLogLevel: Level
) : KLogger {

    override fun at(level: Level, marker: Marker?, block: KLoggingEventBuilder.() -> Unit) {

        when (level) {

            in onceLogLevels -> {
                if (!alreadyLogged.contains(level)) {
                    log.at(level, marker, block)
                    alreadyLogged += level
                }
            }

            successLogLevel -> {
                if (alreadyLogged.isNotEmpty()) {
                    log.at(level, marker, block)
                    alreadyLogged.clear()
                }
            }

            else -> {}
        }
    }

    override fun isLoggingEnabledFor(level: Level, marker: Marker?) = log.isLoggingEnabledFor(level, marker)

    private var alreadyLogged = mutableListOf<Level>()
    private val log = KotlinLogging.logger(name)
}
