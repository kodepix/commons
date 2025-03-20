package io.github.kodepix

import com.typesafe.config.*
import io.github.config4k.*


/**
 * Loads whole config into one data class by lazy function. And prints the config contents in the console.
 *
 * Usage:
 *
 * ```kotlin
 * data class Config(val value: String)
 *
 * val config by extractConfig<Config>()
 * ```
 *
 * @param T config class
 *
 * @sample io.github.kodepix.samples.extractConfigSample
 */
inline fun <reified T : Any> extractConfig() = lazy { config.extract<T>().printConfig() }


/**
 * Prints the config contents in the console or in the log. The printed text is compatible with Kubernetes.
 *
 * @param T config class
 * @param writeToLog specifies when config should be printed to log
 */
fun <T : Any> T.printConfig(writeToLog: Boolean = false) = apply {

    val text = toConfig(this::class.simpleName!!).root()
        .render(ConfigRenderOptions.defaults().setJson(false).setOriginComments(false))
        .fixForK2s()

    val message = "Loaded $text"

    if (writeToLog)
        configLog.info { message }
    else
        print(message)
}

@PublishedApi
internal val configLog by logger()


@PublishedApi
internal var config = ConfigFactory.load()!!
    private set
