package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.cognitive.variables.engine.detectors.VariableDetector
import edu.slapoguzov.emodetect.occ.model.variables.SelfAppraisal
import edu.slapoguzov.emodetect.occ.model.variables.SelfPresumption
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class SelfPresumptionDetector : VariableDetector<SelfPresumption> {
    override fun detect(sentence: Sentence): SelfPresumption? {
        val action = sentence.allWords.find {
            it.characteristics.contains(Characteristic.IS_ACTION) &&
                    !it.characteristics.contains(Characteristic.IS_MODAL)
        } ?: return null
        if (action.valence >= 0.0) return SelfPresumption.DESIRABLE
        if (action.valence < -0.0) return SelfPresumption.UNDESIRABLE
        return null
    }
}