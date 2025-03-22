@file:Suppress("unused")

package io.github.kodepix

import kotlinx.coroutines.*


/**
 * Returns a list containing the results of applying the given [transform] function
 * to each element in the original collection in it`s own coroutine.
 *
 * Waits for all coroutines completed.
 */
suspend fun <T, R> Iterable<T>.asyncMap(transform: suspend (T) -> R) = coroutineScope {
    map { async { transform(it) } }.awaitAll()
}
