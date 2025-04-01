@file:Suppress("ktlint:standard:property-naming", "LocalVariableName", "unused", "UNUSED_VARIABLE")

package io.github.kodepix.samples

import io.github.kodepix.*
import io.github.oshai.kotlinlogging.Level.*
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds


internal fun idSample() {
    val id = Id(123)
    println("id: $id")
    println("id value property: ${id.value}")
    println("id example: ${Id.example}")
}


internal fun castSample() {
    val any: Any = "string"
    val string = any.cast<String>()
}


internal fun loggerSample() {
    val log by logger()
}

internal fun statefulLoggerSample() {
    val log by statefulLogger(onceLogLevels = listOf(ERROR, WARN), successLogLevel = INFO)
}


internal fun extractConfigSample() {

    data class Config(val value: String)

    val config by extractConfig<Config>()
}


internal fun string22Sample() {

    val uuid = uuid("6c7ec513-a7b0-4830-b955-5ad537e9bb01")
    assert(uuid.string22 == "bH7FE6ewSDC5VVrVN-m7AQ")
}


internal fun buildStringSample() {

    val string = buildString {
        +"first"
        +"second"
    }
}


internal fun runUntilSuccessSample() {

    runUntilSuccess {
        // Database connecting, for example.
    }
}


internal fun doAndDelaySample() {

    runBlocking {
        doAndDelay(2.seconds) {
            // Periodical work.
        }
    }
}
