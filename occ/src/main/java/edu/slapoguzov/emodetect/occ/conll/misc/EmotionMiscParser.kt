package edu.slapoguzov.emodetect.occ.conll.misc

import edu.slapoguzov.emodetect.core.conll.misc.MiscParser
import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType

class EmotionMiscParser : MiscParser<EmotionMisc> {

    override fun parse(text: String): EmotionMisc {
        val emotionType = EmotionType.valueOf(text.removePrefix(EmotionMisc.KEY))
        return EmotionMisc(emotionType)
    }

    override fun isSuitable(text: String): Boolean {
        return text.contains(EmotionMisc.KEY)
    }


}
