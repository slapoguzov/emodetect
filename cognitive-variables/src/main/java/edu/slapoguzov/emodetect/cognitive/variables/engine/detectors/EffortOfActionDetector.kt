package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.EffortOfAction
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class EffortOfActionDetector : VariableDetector<EffortOfAction> {
    override fun detect(sentence: Sentence): EffortOfAction {
        val effortOfActionWords = sentence.allWords.filter { it.characteristics.contains(Characteristic.EFFORT_ACTION) }

        if (effortOfActionWords.isNotEmpty()) return EffortOfAction.OBVIOUS
        return EffortOfAction.NOT_OBVIOUS
    }
}