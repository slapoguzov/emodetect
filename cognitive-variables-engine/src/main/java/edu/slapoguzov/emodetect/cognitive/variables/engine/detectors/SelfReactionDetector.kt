package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.cognitive.variables.engine.detectors.VariableDetector
import edu.slapoguzov.emodetect.occ.model.variables.SelfAppraisal
import edu.slapoguzov.emodetect.occ.model.variables.SelfPresumption
import edu.slapoguzov.emodetect.occ.model.variables.SelfReaction
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class SelfReactionDetector : VariableDetector<SelfReaction> {
    override fun detect(sentence: Sentence): SelfReaction? {
        val action = sentence.allWords.find { it.characteristics.contains(Characteristic.IS_ACTION) }
                ?: return null
        if (action.valence > 0.0) return SelfReaction.PLEASED
        if (action.valence < -0.0) return SelfReaction.DISPLEASED
        return null
    }
}