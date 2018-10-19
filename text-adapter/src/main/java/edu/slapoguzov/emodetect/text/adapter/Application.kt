package edu.slapoguzov.emodetect.text.adapter

object Application {
    private val textAdapter = TextAdapter()

    fun run() {
        val text = "Внезапно он проснулся"
        val emotions = textAdapter.detectEmotions(text)
        println(emotions)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

