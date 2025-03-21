@file:Suppress("unused")

package io.github.kodepix


/**
 * Builds new string by populating newly created [StringBuildContext] using provided [builder] and then converting it to [String].
 *
 * Usage:
 *
 * ```kotlin
 * val string = buildString {
 *     +"first"
 *     +"second"
 * }
 * ```
 *
 * @param builder build function
 *
 * @sample io.github.kodepix.samples.buildStringSample
 */
@BuildStringDsl
fun buildString(builder: StringBuildContext.() -> Unit) = run {
    val context = StringBuildContext()
    builder(context)
    context.toString()
}

/**
 * String building context using [StringBuilder].
 */
@BuildStringDsl
class StringBuildContext {

    operator fun String.unaryPlus() = builder.appendLine(this)

    fun deleteLastChar() {
        builder.deleteCharAt(builder.length - 1)
    }

    override fun toString() = builder.toString()

    private val builder = StringBuilder()
}

@DslMarker
annotation class BuildStringDsl


/**
 * Fixes the string to display correctly in Kubernetes.
 * Replaces spaces with `NBSP`.
 */
fun String.fixForK2s(): String = replace(" ", " ")
