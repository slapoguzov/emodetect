package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.EventFamiliarity
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Characteristic.*
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent

class EventFamiliarityDetector : VariableDetector<EventFamiliarity> {
    override fun detect(sentence: Sentence): EventFamiliarity? {
        val words = sentence.allWords.filter {
            it.characteristics.contains(IS_ACTION)
                    || it.characteristics.contains(IS_OBJECT)
        }
        if (words.isEmpty()) return EventFamiliarity.COMMON
        val avgPopularity = words.map { it.popularity }
                .average()
        if (avgPopularity < 0.3) return EventFamiliarity.UNCOMMON
        return EventFamiliarity.COMMON
    }
}