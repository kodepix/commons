package io.github.kodepix

import io.github.oshai.kotlinlogging.*
import io.github.oshai.kotlinlogging.Level.*
import io.kotest.core.spec.style.*
import io.mockk.*


class LoggingSpec : ShouldSpec({

    context("StatefulLog") {

        should("show message each level only once if level in `onceLogLevels`") {

            mockkObject(KotlinLogging)
            val log = mockk<KLogger>()

            every { KotlinLogging.logger(any<String>()) } returns log
            every { log.at(any(), any(), any<KLoggingEventBuilder.() -> Unit>()) } just runs

            val logger by statefulLogger(onceLogLevels = listOf(ERROR, TRACE), successLogLevel = DEBUG)

            logger.error { "error1" }
            logger.error { "error2" }
            logger.trace { "trace1" }
            logger.trace { "trace2" }
            logger.warn { "warn1" }
            logger.warn { "warn2" }

            logger.debug { "debug1" }
            logger.debug { "debug2" }

            verify(exactly = 1) { log.at(ERROR, any(), any<KLoggingEventBuilder.() -> Unit>()) }
            verify(exactly = 1) { log.at(TRACE, any(), any<KLoggingEventBuilder.() -> Unit>()) }
            verify(exactly = 0) { log.at(WARN, any(), any<KLoggingEventBuilder.() -> Unit>()) }

            verify(exactly = 1) { log.at(DEBUG, any(), any<KLoggingEventBuilder.() -> Unit>()) }
        }
    }
})
