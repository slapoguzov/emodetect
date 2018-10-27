package edu.slapoguzov.emodetect.statistics

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import edu.slapoguzov.emodetect.core.getPathToResource
import edu.slapoguzov.emodetect.statistics.entity.ValencedWord
import java.io.File

class ValenceDictionary() {
    private val mapper = ObjectMapper().registerKotlinModule()

    val dictionary: Map<String, ValencedWord>

    init {
        val path = this.javaClass.getPathToResource("valency/valenceDictionary.json")
        val file = File(path)
        val valencedWords = mapper.readValue<List<ValencedWord>>(file)
        dictionary = valencedWords.associateBy { it.token }
    }

    fun getValence(word: String): Double {
        return dictionary[word]?.valence ?: 0.0
    }

    fun getValence(words: List<String>): Double {
        val valencedWords = words
                .mapNotNull { dictionary[it]?.valence }
                .filter { it != 0.0 }
        if (valencedWords.isEmpty()) return 0.0
        return valencedWords.average()
    }

    fun getCountPositiveSense(word: String): Int {
        return dictionary[word]?.numberPositiveSenses ?: 1
    }

    fun getCountNegativeSense(word: String): Int {
        return dictionary[word]?.numberNegativeSenses ?: 1
    }
}