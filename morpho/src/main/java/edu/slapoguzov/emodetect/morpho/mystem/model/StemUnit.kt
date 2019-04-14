package edu.slapoguzov.emodetect.morpho.mystem.model

import edu.slapoguzov.emodetect.morpho.mystem.model.StemGrammem.*

data class StemUnit(
        val lex: String,
        val original: String,
        val grammems: List<StemGrammem>
) {
    val partOfSpeach = grammems.find { StemGrammem.partsOfSpeach.contains(it) }
    val tense = grammems.find { StemGrammem.tenses.contains(it) }
    val animate = if (grammems.any { it == NOUN_PRO || it == ANIMATE }) ANIMATE else grammems.find { it == INANIMATE }
}