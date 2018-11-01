package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.OtherPresumption
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class OtherPresumptionDetector : VariableDetector<OtherPresumption> {
    override fun detect(sentence: Sentence): OtherPresumption? {
        val agent = sentence.allWords.find { it.characteristics.contains(Characteristic.IS_AGENT) }
        val action = sentence.allWords.find { it.characteristics.contains(Characteristic.IS_ACTION) }
        if (agent == null || action == null) return null
        if (action.valence >= 0.0 && agent.valence >= 0.0) return OtherPresumption.DESIRABLE
        if (action.valence < 0.0 && agent.valence < 0.0) return OtherPresumption.DESIRABLE
        if (action.valence >= 0.0 && agent.valence < 0.0) return OtherPresumption.UNDESIRABLE
        if (action.valence < 0.0 && agent.valence >= 0.0) return OtherPresumption.UNDESIRABLE
        return OtherPresumption.DESIRABLE
    }
}