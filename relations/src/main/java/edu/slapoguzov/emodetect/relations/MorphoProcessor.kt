package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.sentence.mystem.MyStemWrapper
import edu.slapoguzov.emodetect.sentence.mystem.model.StemUnit

class MorphoProcessor(
        val myStem: MyStemWrapper
) {
    fun process(text: String): Map<Int, StemUnit> {
        return myStem.analyze(text)
    }
}