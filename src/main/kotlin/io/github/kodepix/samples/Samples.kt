@file:Suppress("ktlint:standard:property-naming", "LocalVariableName", "unused", "UNUSED_VARIABLE")

package io.github.kodepix.samples

import io.github.kodepix.*


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


internal fun extractConfigSample() {

    data class Config(val value: String)

    val config by extractConfig<Config>()
}
