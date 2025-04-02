package io.github.kodepix

import com.fasterxml.jackson.annotation.JsonInclude.Include.*
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.SerializationFeature.*
import com.fasterxml.jackson.databind.json.*
import com.fasterxml.jackson.databind.module.*
import com.fasterxml.jackson.datatype.jsr310.*
import com.fasterxml.jackson.module.kotlin.*
import kotlin.reflect.*


/**
 * Serializes any value as JSON-string.
 */
fun Any.toJson(): String = internalObjectMapper.writeValueAsString(this)


/**
 * Deserializes JSON-string to object.
 */
inline fun <reified T> String.fromJson(): T = internalObjectMapper.readValue(this)


/**
 * Deserializes JSON-string to object.
 *
 * @param clazz class of object
 */
fun String.fromJson(clazz: KClass<*>): Any = internalObjectMapper.readValue(this, clazz.java)


/**
 * Returns created [JsonMapper] instance.
 *
 * Usage:
 *
 * ```kotlin
 * val objectMapper = objectMapper {}
 * ```
 *
 * @param configure configuration block
 *
 * @sample io.github.kodepix.samples.objectMapperSample
 */
fun objectMapper(configure: JsonMapper.() -> Unit) = jacksonObjectMapper().apply {
    configure()
    configure(this as JsonMapper)
    internalObjectMapper = this
}

@PublishedApi
internal lateinit var internalObjectMapper: JsonMapper


/**
 * Base [ObjectMapper] configuring.
 *
 * + Registered [JavaTimeModule]
 * + Registered [Id] serializer/deserializer
 * + Serialization inclusion [ALWAYS]
 * + Disabled [WRITE_DATES_AS_TIMESTAMPS]
 *
 * @param config additional configure block
 */
fun ObjectMapper.configure(config: ObjectMapper.() -> Unit = {}) = run {

    registerModule(JavaTimeModule())
    setSerializationInclusion(ALWAYS)
    disable(WRITE_DATES_AS_TIMESTAMPS)

    registerModule(
        SimpleModule().apply {
            addSerializer(Id::class.java, IdSerializer())
            addDeserializer(Id::class.java, IdDeserializer())
        }
    )

    config(this)
    this
}

private class IdSerializer : JsonSerializer<Id>() {
    override fun serialize(value: Id, generator: JsonGenerator, serializerProvider: SerializerProvider) = generator.writeNumber(value.value)
}

private class IdDeserializer : JsonDeserializer<Id>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = Id(p.codec.readValue(p, Int::class.java))
}
