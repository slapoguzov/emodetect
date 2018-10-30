package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.relations.model.connl.DependencyType.*
import edu.slapoguzov.emodetect.relations.model.connl.DependencyType.OTHER
import edu.slapoguzov.emodetect.relations.model.connl.Feats
import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach
import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach.*
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorResult
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Characteristic.*
import mu.KLogging

class RelationProcessor {
    fun process(parameters: RelationProcessorParameters): RelationProcessorResult {
        val (srcChars, targetChars) = when (parameters.dependencyType) {
            NOMINAL_SUBJECT -> processNominalSubjectRelation()
            ADVERBIAL_MODIFIER -> processAdverbialModifierRelation(parameters)
            NOMINAL_MODIFIER, OBJECT -> processObjectRelation(parameters)
            OTHER -> processOtherRelation(parameters)
            else -> emptyList<Characteristic>() to emptyList()
        }
        return RelationProcessorResult(srcChars, targetChars)
    }

    private fun processOtherRelation(parameters: RelationProcessorParameters): Pair<List<Characteristic>, List<Characteristic>> {
        if (parameters.src.partOfSpeach == VERB && parameters.target.lemma == "не")
            return listOf(Characteristic.NEGATION) to emptyList()
        return emptyList<Characteristic>() to emptyList()
    }

    private fun processObjectRelation(parameters: RelationProcessorParameters): Pair<List<Characteristic>, List<Characteristic>> {
        if (parameters.dependencyType == NOMINAL_MODIFIER
                && parameters.src.partOfSpeach != VERB) return emptyList<Characteristic>() to emptyList()
        return listOf(IS_ACTION) to listOf(IS_OBJECT)
    }

    private fun processNominalSubjectRelation() = listOf(IS_ACTION) to listOf(IS_AGENT)

    private fun processAdverbialModifierRelation(parameters: RelationProcessorParameters): Pair<List<Characteristic>, List<Characteristic>> {
        if (parameters.src.partOfSpeach != VERB) return emptyList<Characteristic>() to listOf(IS_ADV)
        if (parameters.target.feats.contains(Feats.DEGREE)) return listOf(IS_ACTION) to listOf(IS_ADV, EFFORT_ACTION)
        return listOf(IS_ACTION) to listOf(IS_ADV)
    }

    private companion object : KLogging()
}