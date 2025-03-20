package io.github.kodepix


/**
 * Fixes the string to display correctly in Kubernetes.
 * Replaces spaces with 'NBSP'.
 */
fun String.fixForK2s(): String = replace(" ", " ")
