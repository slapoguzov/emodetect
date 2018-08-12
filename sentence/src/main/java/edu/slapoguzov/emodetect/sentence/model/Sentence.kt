package edu.slapoguzov.emodetect.sentence.model

data class Sentence(
    val clauses: List<Clause>,
    override val valence: Double
): Valenced