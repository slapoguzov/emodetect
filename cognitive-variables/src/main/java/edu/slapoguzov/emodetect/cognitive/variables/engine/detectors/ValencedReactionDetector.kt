package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.ValencedReaction
import edu.slapoguzov.emodetect.sentence.model.Sentence

class ValencedReactionDetector : VariableDetector<ValencedReaction> {
    override fun detect(sentence: Sentence): ValencedReaction? {
        if (sentence.allWords.any { it.valence != 0.0 }) return ValencedReaction.TRUE
        return ValencedReaction.FALSE
    }
}