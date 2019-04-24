package edu.slapoguzov.emodetect.occ.conll.misc

import edu.slapoguzov.emodetect.core.conll.misc.Misc
import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType

class EmotionMisc(val emotionType: EmotionType) : Misc {
    override fun toString(): String {
        return KEY + emotionType.name
    }

    companion object {
        const val KEY = "Emotion="
    }
}
