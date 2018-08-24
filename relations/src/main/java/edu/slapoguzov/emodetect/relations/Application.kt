package edu.slapoguzov.emodetect.relations

object Application {
    fun run() {
        RemoteSyntaxNetExtractor().extract("")
    }
}

fun main(args: Array<String>) {
    Application.run()
}