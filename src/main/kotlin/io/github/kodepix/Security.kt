@file:Suppress("unused")

package io.github.kodepix

import java.security.*
import java.util.*


/**
 * Returns `MD5` digest of [this] represented as [UUID].
 */
fun ByteArray.md5() = uuid(MessageDigest.getInstance("MD5").digest(this))
