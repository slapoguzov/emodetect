package edu.slapoguzov.emodetect.statistics

import edu.slapoguzov.emodetect.statistics.utils.getPopularity

class StatisticsComponent {

    private val valenceDictionary = ValenceDictionary()
    private val popularityDictionary = PopularityDictionaryFactory.instance

    fun getValence(word: String): Double {
        return valenceDictionary.getValence(word)
    }

    fun getValence(words: List<String>): Double {
        return valenceDictionary.getValence(words)
    }

    fun getCountPositiveSense(word: String): Int {
        return valenceDictionary.getCountPositiveSense(word)
    }

    fun getCountNegativeSense(word: String): Int {
        return valenceDictionary.getCountNegativeSense(word)
    }

    fun getPopularity(word: String): Double {
        val usage = popularityDictionary.words[word]?.numberOfUse ?: 0
        val totalUsage = usage + popularityDictionary.words.values.sumBy { wordPopularity -> wordPopularity.useWithAgents.filter { it.lemma == word }.sumBy { it.numberOfUse } + wordPopularity.useWithObjects.filter { it.lemma == word }.sumBy { it.numberOfUse } }
        return totalUsage.toDouble() / popularityDictionary.totalOfUse
    }

    fun getPopularity(words: List<String>): Double {
        return popularityDictionary.words.getPopularity(words)
    }
}