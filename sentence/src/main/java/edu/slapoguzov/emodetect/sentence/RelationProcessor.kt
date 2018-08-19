package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.relations.model.connl.DependencyType.*
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorResult
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.stanford.nlp.trees.EnglishGrammaticalRelations
import mu.KLogging

class RelationProcessor {
    fun process(parameters: RelationProcessorParameters): RelationProcessorResult {
        val (srcChars, targetChars) = when (parameters.dependencyType) {
            NOMINAL_SUBJECT -> listOf(Characteristic.IS_ACTION) to listOf(Characteristic.IS_AGENT)
            ADVERBIAL_MODIFIER -> listOf(Characteristic.IS_ACTION) to listOf(Characteristic.IS_ADV)
            OBJECT -> listOf(Characteristic.IS_ACTION) to listOf(Characteristic.IS_OBJECT)

            else -> emptyList<Characteristic>() to emptyList()
        }
        return RelationProcessorResult(srcChars, targetChars)
    }

    private companion object : KLogging()
}