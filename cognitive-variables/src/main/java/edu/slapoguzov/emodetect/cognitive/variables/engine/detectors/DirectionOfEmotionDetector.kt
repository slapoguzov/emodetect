package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.AgentFondness
import edu.slapoguzov.emodetect.occ.model.variables.DirectionOfEmotion
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence

class DirectionOfEmotionDetector : VariableDetector<DirectionOfEmotion> {
    override fun detect(sentence: Sentence): DirectionOfEmotion {
        val animateObjects = sentence.allWords.filter {
            it.characteristics.contains(Characteristic.IS_OBJECT)
                    && it.characteristics.contains(Characteristic.ANIMATE)
        }

        if (animateObjects.isNotEmpty()) return DirectionOfEmotion.OTHER
        return DirectionOfEmotion.SELF
    }
}