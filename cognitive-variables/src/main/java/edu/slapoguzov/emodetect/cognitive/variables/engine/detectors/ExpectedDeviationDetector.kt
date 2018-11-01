package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.EventFamiliarity
import edu.slapoguzov.emodetect.occ.model.variables.ExpectedDeviation
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent

class ExpectedDeviationDetector(private val statisticsComponent: StatisticsComponent) : VariableDetector<ExpectedDeviation> {
    override fun detect(sentence: Sentence): ExpectedDeviation {
        val actions = sentence.allWords.filter { it.characteristics.contains(Characteristic.IS_ACTION) }
        val agents = sentence.allWords.filter { it.characteristics.contains(Characteristic.IS_AGENT) }
        if (actions.isEmpty() || agents.isEmpty()) return ExpectedDeviation.LOW
        val collocations = actions.flatMap { action -> agents.map { obj -> action to obj } }
        val usedTogether = collocations
                .map { statisticsComponent.getPopularity(listOf(it.first.lemma!!, it.second.lemma!!)) }
                .any { it > 0.0 }
        if (usedTogether) return ExpectedDeviation.HIGH
        return ExpectedDeviation.LOW
    }
}