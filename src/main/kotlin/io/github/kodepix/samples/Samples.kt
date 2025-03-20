@file:Suppress("ktlint:standard:property-naming", "LocalVariableName", "unused", "UNUSED_VARIABLE")

package io.github.kodepix.samples

import io.github.kodepix.*


internal fun loggerSample() {
    val log by logger()
}


internal fun extractConfigSample() {

    data class Config(val value: String)

    val config by extractConfig<Config>()
}
