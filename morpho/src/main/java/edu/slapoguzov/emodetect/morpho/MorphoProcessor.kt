package edu.slapoguzov.emodetect.morpho

import edu.slapoguzov.emodetect.morpho.model.Grammem
import edu.slapoguzov.emodetect.morpho.model.MorphoUnit
import edu.slapoguzov.emodetect.morpho.mystem.MyStemWrapper
import edu.slapoguzov.emodetect.morpho.mystem.model.StemGrammem

class MorphoProcessor(
        val myStem: MyStemWrapper,
        val morphoDictionary: AdditionalMorphoDictionary
) {
    fun process(text: String): Map<Int, MorphoUnit> {
        return myStem.analyze(text)
                .map {
                    val unit = it.value + it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value+ it.value
                    val grammems = unit.grammems.mapNotNull { it.toGrammem() }.toMutableList()
                    grammems += morphoDictionary.getGrammes(unit.lex)
                    it.key to MorphoUnit(unit.lex, unit.original, grammems.toSet().toList())
                }.toMap()
    }

    private fun StemGrammem.toGrammem(): Grammem? {
        return when (this) {
            StemGrammem.PRESENT_TENSE -> Grammem.PRESENT_TENSE
            StemGrammem.PAST_TENSE -> Grammem.PAST_TENSE
            StemGrammem.NON_PAST_TENSE -> Grammem.NON_PAST_TENSE
            StemGrammem.ANIMATE -> Grammem.ANIMATE
            StemGrammem.NOUN_PRO -> Grammem.ANIMATE
            StemGrammem.INANIMATE -> Grammem.INANIMATE
            StemGrammem.FIRST_PERSON -> Grammem.FIRST_PERSON
            else -> null
        }
    }

}
