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
