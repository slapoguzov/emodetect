package edu.slapoguzov.emodetect.sentence.model

data class Collocation(
        val words: List<Word>,
        val frequency: Double,
        override val valence: Double
): Valenced