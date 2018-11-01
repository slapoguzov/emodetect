package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.Prospect
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent

class ProspectDetector(private val statisticsComponent: StatisticsComponent) : VariableDetector<Prospect> {
    override fun detect(sentence: Sentence): Prospect? {
        val action = sentence.allWords.find { it.characteristics.contains(Characteristic.IS_ACTION) }
                ?: return Prospect.NEUTRAL
        val countNegativeSense = statisticsComponent.getCountNegativeSense(action.lemma!!)
        val countPositiveSense = statisticsComponent.getCountPositiveSense(action.lemma!!)

        if (countPositiveSense > countNegativeSense) return Prospect.POSITIVE
        if (countPositiveSense < countNegativeSense) return Prospect.NEGATIVE
        return Prospect.NEUTRAL
    }
}