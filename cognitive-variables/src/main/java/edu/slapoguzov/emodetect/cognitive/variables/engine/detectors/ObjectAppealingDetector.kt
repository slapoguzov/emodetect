package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.ExpectedDeviation
import edu.slapoguzov.emodetect.occ.model.variables.ObjectAppealing
import edu.slapoguzov.emodetect.occ.model.variables.ObjectAppealing.*
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent

class ObjectAppealingDetector : VariableDetector<ObjectAppealing> {
    override fun detect(sentence: Sentence): ObjectAppealing? {
        val objects = sentence.allWords.filter { it.characteristics.contains(Characteristic.IS_OBJECT) }
        if (objects.isEmpty()) return NEUTRAL
        val values = objects.map {
            if (it.popularity < 0.3 && it.valence > 0.0) return@map ATTRACTIVE
            if (it.popularity > 0.5 && it.valence < 0.0) return@map NOT_ATTRACTIVE
            NEUTRAL
        }
        val numberAttractive = values.count { it == ATTRACTIVE }
        val numberNotAttractive = values.count { it == NOT_ATTRACTIVE }
        return when {
            numberAttractive > numberNotAttractive -> ATTRACTIVE
            numberAttractive < numberNotAttractive -> NOT_ATTRACTIVE
            else -> NEUTRAL
        }

    }
}