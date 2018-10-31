package edu.slapoguzov.emodetect.backend

object Application {
    private val textAdapter = EmotionService()

    fun run() {
        val text = "Иван не получил зарплату . "
        val emotions = textAdapter.detectEmotions(text)
        println(emotions)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

