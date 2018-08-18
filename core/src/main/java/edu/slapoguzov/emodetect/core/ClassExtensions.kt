package edu.slapoguzov.emodetect.core

fun <T> Class<T>.getPathToResource(resourceName: String): String {
    return this.classLoader.getResource(resourceName).toURI().path
}