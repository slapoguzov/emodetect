package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.EventDeservingness
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class EventDeservingnessDetector : VariableDetector<EventDeservingness> {
    override fun detect(sentence: Sentence): EventDeservingness? {
        val actions = sentence.allWords.filter { it.characteristics.contains(Characteristic.IS_ACTION) }

        if (actions.isEmpty()) return null

        val avgValency = actions.map { it.valence }.average()

        if (avgValency > 0.5) return EventDeservingness.HIGH
        if (avgValency < -0.5) return EventDeservingness.LOW
        return null
    }
}