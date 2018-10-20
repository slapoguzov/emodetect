package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.ObjectFondness
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class ObjectFondnessDetector : VariableDetector<ObjectFondness> {
    override fun detect(sentence: Sentence): ObjectFondness {
        val positiveAgents = sentence.allWords.filter {
            it.characteristics.contains(Characteristic.IS_OBJECT)
                    && it.valence > 0
        }

        if (positiveAgents.isNotEmpty()) return ObjectFondness.LIKED
        return ObjectFondness.NOT_LIKED
    }
}