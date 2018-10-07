package edu.slapoguzov.emodetect.morpho.mystem.model

data class StemUnit(
        val lex: String,
        val original: String,
        val grammems: List<StemGrammem>
) {
    val partOfSpeach = grammems.find { StemGrammem.partsOfSpeach.contains(it) }
    val tense = grammems.find { StemGrammem.tenses.contains(it) }
}