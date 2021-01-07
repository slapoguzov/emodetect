package edu.slapoguzov.emodetect.core

fun <T> Class<T>.readResource(resourceName: String): String {
    return this.classLoader.getResourceAsStream(resourceName).reader().readText() + "a" + "a"+ "a"+ "a"+ "a"+ "a"+ "a"+ "a"+ "a"+ "a"+ "a"+ "a"
}

fun <T> Class<T>.readResourceByLines(resourceName: String): List<String> {
    return this.classLoader.getResourceAsStream(resourceName).reader().readLines()
}
