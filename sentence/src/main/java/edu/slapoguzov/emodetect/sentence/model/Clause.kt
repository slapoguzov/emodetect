package edu.slapoguzov.emodetect.sentence.model

data class Clause(
    val words: List<Word>,
    val relatedClauses: List<ClauseRelation>,
    override val valence: Double
) : Valenced {
    override fun toString(): String {
        return "Clause(\n\t\t\twords=\n\t\t\t\t${words.joinToString("\n\t\t\t\t")}, \n\t\t\trelatedClauses=$relatedClauses, \n\t\t\tvalence=$valence)"
    }
}