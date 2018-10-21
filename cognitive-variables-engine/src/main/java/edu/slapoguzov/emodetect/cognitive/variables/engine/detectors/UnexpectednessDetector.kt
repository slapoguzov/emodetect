package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.Unexpectedness
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class UnexpectednessDetector : VariableDetector<Unexpectedness> {
    override fun detect(sentence: Sentence): Unexpectedness? {
        sentence.allWords.find { it.characteristics.contains(Characteristic.IS_SUDDENLY) } ?: Unexpectedness.FALSE
        return Unexpectedness.TRUE
    }
}