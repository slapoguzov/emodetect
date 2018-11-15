package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.OtherPresumption
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class OtherPresumptionDetector : VariableDetector<OtherPresumption> {
    override fun detect(sentence: Sentence): OtherPresumption? {
        val agent = sentence.allWords.find { it.characteristics.contains(Characteristic.IS_AGENT) }
        val action = sentence.allWords.find { it.characteristics.contains(Characteristic.IS_ACTION) }

        val actionValency = action?.valence ?: 0.0
        val agentValency = agent?.valence ?: 0.0

        if (actionValency >= 0.0 && agentValency >= 0.0) return OtherPresumption.DESIRABLE
        if (actionValency < 0.0 && agentValency < 0.0) return OtherPresumption.DESIRABLE
        if (actionValency >= 0.0 && agentValency < 0.0) return OtherPresumption.UNDESIRABLE
        if (actionValency < 0.0 && agentValency >= 0.0) return OtherPresumption.UNDESIRABLE

        return OtherPresumption.DESIRABLE
    }
}