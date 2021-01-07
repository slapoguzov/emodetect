package edu.slapoguzov.emodetect.core

fun <T> Class<T>.readResource(ResourceName: String): String {
    return this.classLoader.getResourceAsStream(resourceName).reader().readText()
}
fun <T> Class<T>.readResourceByLines(resourceName: String): List<String> {
    return this.classLoader.getResourceAsStream(resourceName).reader().readLines()
}

