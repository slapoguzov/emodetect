package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.EventFamiliarity
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent

class EventFamiliarityDetector(private val statisticsComponent: StatisticsComponent) : VariableDetector<EventFamiliarity> {
    override fun detect(sentence: Sentence): EventFamiliarity? {
        val actions = sentence.allWords.filter { it.characteristics.contains(Characteristic.IS_ACTION) }
        val objects = sentence.allWords.filter { it.characteristics.contains(Characteristic.IS_OBJECT) }
        if (actions.isEmpty() || objects.isEmpty()) return EventFamiliarity.COMMON
        val collocations = actions.flatMap { action -> objects.map { obj -> action to obj } }
        val avgPopularity = collocations
                .map { statisticsComponent.getPopularity(listOf(it.first.lemma!!, it.second.lemma!!)) }
                .average()
        if (avgPopularity < 0.3) return EventFamiliarity.UNCOMMON
        return EventFamiliarity.COMMON
    }
}