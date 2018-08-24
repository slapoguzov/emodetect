package edu.slapoguzov.emodetect.statistics

import edu.slapoguzov.emodetect.relations.RelationsExtractor
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.relations.model.connl.DependencyType
import edu.slapoguzov.emodetect.relations.model.connl.DependencyType.*
import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach
import edu.slapoguzov.emodetect.statistics.entity.WordPopularity
import edu.slapoguzov.emodetect.statistics.entity.WordUse
import edu.slapoguzov.emodetect.statistics.utils.addSingle
import edu.slapoguzov.emodetect.statistics.utils.addWithAgent
import edu.slapoguzov.emodetect.statistics.utils.addWithObject

class WordPopularityExtractor {

    val relationExtractor: RelationsExtractor = RemoteSyntaxNetExtractor()

    fun extractWordPopularities(text: String): Map<String, WordPopularity> {
        val wordPopularities = mutableMapOf<String, WordPopularity>()
        val connlSentence = relationExtractor.extract(text)
        connlSentence.allRelations.forEach {
            val srcLemma = it.src.lemma!!
            val targetLemma = it.target.lemma!!
            when (it.dependencyType) {
                NOMINAL_SUBJECT -> wordPopularities.addWithAgent(srcLemma, WordUse(targetLemma))
                OBJECT -> wordPopularities.addWithObject(srcLemma, WordUse(targetLemma))
                else -> if (it.src.partOfSpeach == PartOfSpeach.VERB) {
                    wordPopularities.addSingle(srcLemma)
                }
            }
        }
        return wordPopularities
    }
}