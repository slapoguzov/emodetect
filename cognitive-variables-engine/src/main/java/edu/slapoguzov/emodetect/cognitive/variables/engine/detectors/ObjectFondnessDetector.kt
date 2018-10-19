package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.AgentFondness
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class ObjectFondnessDetector : VariableDetector<AgentFondness> {
    override fun detect(sentence: Sentence): AgentFondness {
        val positiveAgents = sentence.allWords.filter {
            it.characteristics.contains(Characteristic.IS_OBJECT)
                    && it.valence > 0
        }

        if (positiveAgents.isNotEmpty()) return AgentFondness.LIKED
        return AgentFondness.NOT_LIKED
    }
}