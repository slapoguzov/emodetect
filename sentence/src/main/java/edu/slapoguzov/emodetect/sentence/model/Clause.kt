package edu.slapoguzov.emodetect.sentence.model

data class Clause(
    val words: List<Word>,
    val relatedClauses: List<ClauseRelation>,
    override val valence: Double
) : Valenced