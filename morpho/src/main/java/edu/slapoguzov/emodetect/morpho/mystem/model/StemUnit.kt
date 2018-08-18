package edu.slapoguzov.emodetect.sentence.mystem.model

data class StemUnit(
        val lex: String,
        val original: String,
        val grammems: List<Grammem>
) {
    val partOfSpeach = grammems.find { Grammem.partsOfSpeach.contains(it) }
}