@file:Suppress("unused")

package io.github.kodepix

import java.nio.*
import java.util.*
import kotlin.io.encoding.*
import kotlin.io.encoding.Base64


/**
 * Returns a type 4 (pseudo randomly generated) UUID. The UUID is generated using a cryptographically strong pseudo random number generator.
 */
fun uuid() = UUID.randomUUID()!!


/**
 * Creates a UUID from the string standard representation as described in the toString() method.
 *
 * @param value a string that specifies a UUID
 */
fun uuid(value: String) = UUID.fromString(value)!!


/**
 * Constructs a new `UUID` using the specified data.
 *
 * @param value an array of bytes whose first 16 bytes are used to create UUID
 */
fun uuid(value: ByteArray) = run {

    require(value.size >= 16)

    ByteBuffer.wrap(value).let { UUID(it.long, it.long) }
}


/**
 * Byte array representation of UUID.
 */
val UUID.bytes: ByteArray
    get() = ByteBuffer.wrap(ByteArray(16))
        .apply {
            putLong(mostSignificantBits)
            putLong(leastSignificantBits)
        }
        .array()


/**
 * Returns an url safe string of 22 characters formed from a given [UUID].
 *
 * Usage:
 *
 * ```kotlin
 * val uuid = uuid("6c7ec513-a7b0-4830-b955-5ad537e9bb01")
 * assert(uuid.string22 == "bH7FE6ewSDC5VVrVN-m7AQ")
 * ```
 *
 * @sample io.github.kodepix.samples.string22Sample
 */
@OptIn(ExperimentalEncodingApi::class)
val UUID.string22 get() = String(Base64.Default.UrlSafe.encodeToByteArray(bytes), 0, 22)


/**
 * UUID from the `00000000-0000-0000-0000-000000000000` string.
 */
val zeroUuid = uuid("00000000-0000-0000-0000-000000000000")
