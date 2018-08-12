package edu.slapoguzov.emodetect.sentence.model

data class ClauseRelation(
        val clause: Clause,
        val type: RelationType,
        val conjunction: Word
)