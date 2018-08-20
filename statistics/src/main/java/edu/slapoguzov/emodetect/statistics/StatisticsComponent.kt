package edu.slapoguzov.emodetect.statistics

import com.fasterxml.jackson.databind.ObjectMapper

class StatisticsComponent {

    private val mapper = ObjectMapper()

    val valenceDictionary = ValenceDictionary()

    fun getValence(word: String): Double {
        return valenceDictionary.getValence(word)
    }

    fun getValence(words: List<String>): Double {
        return valenceDictionary.getValence(words)
    }

    fun getCountPositiveSense(word: String): Int {
        return 1
    }

    fun getCountNegativeSense(word: String): Int {
        return 1
    }

    fun getPopularity(word: String): Double {
        return 0.0
    }

    fun getPopularity(words: List<String>): Double {
        return 0.0
    }
}