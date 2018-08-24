package edu.slapoguzov.emodetect.statistics.utils

import edu.slapoguzov.emodetect.statistics.entity.WordPopularity
import edu.slapoguzov.emodetect.statistics.entity.WordUse

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