package edu.slapoguzov.emodetect.statistics.utils

import edu.slapoguzov.emodetect.relations.RelationsExtractor
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.relations.model.connl.DependencyType.*
import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach
import edu.slapoguzov.emodetect.statistics.entity.WordPopularity
import edu.slapoguzov.emodetect.statistics.entity.WordUse

class WordPopularityExtractor {

    private val relationExtractor: RelationsExtractor = RemoteSyntaxNetExtractor()

    fun extractWordPopularities(text: String): Map<String, WordPopularity> {
        val wordPopularities = mutableMapOf<String, WordPopularity>()
        try {
            val connlSentence = relationExtractor.extract(text)
            connlSentence.allRelations.forEach {
                val srcLemma = it.src.lemma
                val targetLemma = it.target.lemma
                when (it.dependencyType) {
                    NOMINAL_SUBJECT -> wordPopularities.addWithAgent(srcLemma, WordUse(targetLemma))
                    OBJECT -> wordPopularities.addWithObject(srcLemma, WordUse(targetLemma))
                    else -> if (it.src.partOfSpeach == PartOfSpeach.VERB) {
                        wordPopularities.addSingle(srcLemma)
                    }
                }
            }
            return wordPopularities
        } catch (e: Exception) {
            return emptyMap()
        }
    }
}