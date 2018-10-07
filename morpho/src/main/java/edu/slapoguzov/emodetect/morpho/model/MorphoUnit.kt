package edu.slapoguzov.emodetect.morpho.model

import edu.slapoguzov.emodetect.morpho.mystem.model.StemGrammem

data class MorphoUnit(
        val lex: String,
        val original: String,
        val grammems: List<Grammem>
)