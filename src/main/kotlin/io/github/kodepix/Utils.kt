@file:Suppress("unused")

package io.github.kodepix

import java.lang.management.*


/**
 * IntelliJ IDEA debug detector.
 */
val debuggerConnected by lazyTry { ManagementFactory.getRuntimeMXBean().inputArguments.toString().contains("jdwp") }

/**
 * IntelliJ IDEA running detector.
 */
val runningFromIntelliJ by lazyTry { Id::class.java.classLoader.loadClass("com.intellij.rt.execution.application.AppMainV2") != null }


private inline fun lazyTry(crossinline fn: () -> Boolean) = lazy {
    try {
        fn()
    } catch (_: Throwable) {
        false
    }
}
