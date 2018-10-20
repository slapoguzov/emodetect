package edu.slapoguzov.emodetect.text.adapter

object Application {
    private val textAdapter = TextAdapter()

    fun run() {
        //val text = "Внезапно Маша смогла поздравить Ивана с выигрышем . "
        val text = "Но при мужчине ни одна приличная женщина не пойдет лазить по распродажам . "
        val emotions = textAdapter.detectEmotions(text)
        println(emotions)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

