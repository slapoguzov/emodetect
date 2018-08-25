package edu.slapoguzov.emodetect.statistics.utils

import edu.slapoguzov.emodetect.statistics.entity.WordPopularity
import edu.slapoguzov.emodetect.statistics.entity.WordUse


fun Map<String, WordPopularity>.getPopularity(words: List<String>): Double {
    if (words.size > 2) TODO()
    val (first, second) = words
    return this.getPopularity(first, second) + this.getPopularity(second, first)
}

private fun Map<String, WordPopularity>.getPopularity(word1: String, word2: String): Double {
    val (lemma, numberOfUse, useWithAgents, useWithObjects) = this[word1] ?: return 0.0
    val numberUseWithAgents = useWithAgents.filter { it.lemma == word2 }.sumBy { it.numberOfUse }
    val numberUseWithObjects = useWithObjects.filter { it.lemma == word2 }.sumBy { it.numberOfUse }
    return (numberUseWithAgents + numberUseWithObjects) / numberOfUse.toDouble()
}

fun MutableMap<String, WordPopularity>.merge(other: Map<String, WordPopularity>) {
    if (other.isEmpty()) return
    other.forEach { this.merge(it.key, it.value) }
}

fun MutableMap<String, WordPopularity>.merge(lemma: String, word: WordPopularity) {
    val actual = this[lemma]
    if (actual == null) {
        this[lemma] = word
        return
    }

    word.useWithObjects.forEach { this.addWithObject(lemma, it) }
    word.useWithAgents.forEach { this.addWithAgent(lemma, it) }

    val useWithObjects = word.useWithObjects.sumBy { it.numberOfUse }
    val useWithAgents = word.useWithAgents.sumBy { it.numberOfUse }

    actual.numberOfUse += (word.numberOfUse - useWithObjects - useWithAgents)
}

fun MutableMap<String, WordPopularity>.addSingle(lemma: String) {
    val actual = this[lemma]
    if (actual != null) {
        actual.numberOfUse += 1
        return
    }
    this[lemma] = WordPopularity(lemma, 1)
}

fun MutableMap<String, WordPopularity>.addWithAgent(lemma: String, wordUse: WordUse) {
    val actual = this[lemma]
    if (actual != null) {
        actual.addAgent(wordUse)
        return
    }
    val wordPopularity = WordPopularity(lemma, 0).also {
        it.addAgent(wordUse)
    }
    this[lemma] = wordPopularity
}

fun MutableMap<String, WordPopularity>.addWithObject(lemma: String, wordUse: WordUse) {
    val actual = this[lemma]
    if (actual != null) {
        actual.addObject(wordUse)
        return
    }
    val wordPopularity = WordPopularity(lemma, 0).also {
        it.addObject(wordUse)
    }
    this[lemma] = wordPopularity
}