package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorResult
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Word
import edu.stanford.nlp.trees.EnglishGrammaticalRelations
import edu.stanford.nlp.trees.GrammaticalRelation
import mu.KLogging

class RelationProcessor {
    fun process(parameters: RelationProcessorParameters): RelationProcessorResult {
        val (srcChars, targetChars) = when (parameters.relation) {
            EnglishGrammaticalRelations.NOMINAL_SUBJECT -> listOf(Characteristic.IS_AGENT) to listOf(Characteristic.IS_ACTION)
            else -> emptyList<Characteristic>() to emptyList()
        }
        return RelationProcessorResult(srcChars, targetChars)
    }

    private companion object : KLogging()
}