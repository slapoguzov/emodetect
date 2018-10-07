package edu.slapoguzov.emodetect.morpho

import edu.slapoguzov.emodetect.morpho.model.Grammem
import edu.slapoguzov.emodetect.morpho.model.MorphoUnit
import edu.slapoguzov.emodetect.morpho.mystem.MyStemWrapper
import edu.slapoguzov.emodetect.morpho.mystem.model.StemGrammem
import edu.slapoguzov.emodetect.morpho.mystem.model.StemUnit
import java.lang.UnsupportedOperationException

class MorphoProcessor(
        val myStem: MyStemWrapper,
        val morphoDictionary: AdditionalMorphoDictionary
) {
    fun process(text: String): Map<Int, MorphoUnit> {
        return myStem.analyze(text)
                .map {
                    val unit = it.value
                    val grammems = unit.tense?.let { mutableListOf(it.toGrammem()) } ?: mutableListOf()
                    grammems += morphoDictionary.getGrammes(unit.lex)
                    it.key to MorphoUnit(unit.lex, unit.original, grammems.toList())
                }.toMap()
    }

    private fun StemGrammem.toGrammem(): Grammem {
        return when (this) {
            StemGrammem.PRESENT_TENSE -> Grammem.PRESENT_TENSE
            StemGrammem.PAST_TENSE -> Grammem.PAST_TENSE
            StemGrammem.NON_PAST_TENSE -> Grammem.NON_PAST_TENSE
            else -> throw UnsupportedOperationException()
        }
    }
}