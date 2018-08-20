package edu.slapoguzov.emodetect.statistics.entity

data class ValencedWord(
        val token: String,
        val valence: Double,
        val numberPositiveSenses: Int,
        val numberNegativeSenses: Int,
        val numberNeutralSenses: Int
)