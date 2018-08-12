package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.sentence.mystem.MyStemWrapper
import edu.slapoguzov.emodetect.sentence.mystem.model.StemUnit
import ru.stachek66.nlp.mystem.holding.MyStem

class MorphoProcessor(
        val myStem: MyStemWrapper
) {
    fun process(text: String): Map<Int, StemUnit> {
        return myStem.analyze(text)
    }
}