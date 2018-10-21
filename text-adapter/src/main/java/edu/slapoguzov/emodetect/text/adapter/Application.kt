package edu.slapoguzov.emodetect.text.adapter

object Application {
    private val textAdapter = TextAdapter()

    fun run() {
        //val text = "Внезапно Маша смогла поздравить Ивана с выигрышем . "
        val text = "Иван не получил зарплату . "
        val emotions = textAdapter.detectEmotions(text)
        println(emotions)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

