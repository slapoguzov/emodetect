package edu.slapoguzov.emodetect.statistics.entity

import com.fasterxml.jackson.annotation.JsonIgnore

data class WordPopularity(
        val lemma: String,
        var numberOfUse: Int,
        val useWithAgents: MutableList<WordUse> = mutableListOf(),
        val useWithObjects: MutableList<WordUse> = mutableListOf()
) {
    fun addAgent(wordUse: WordUse) {
        numberOfUse += wordUse.numberOfUse
        useWithAgents.add(wordUse)
    }

    fun addObject(wordUse: WordUse) {
        numberOfUse += wordUse.numberOfUse
        useWithObjects.add(wordUse)
    }
}