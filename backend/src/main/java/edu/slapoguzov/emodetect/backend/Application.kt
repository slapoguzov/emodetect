package edu.slapoguzov.emodetect.backend

import io.micronaut.runtime.Micronaut

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("edu.slapoguzov.emodetect.backend")
                .mainClass(Application.javaClass)
                .start()
    }
}


