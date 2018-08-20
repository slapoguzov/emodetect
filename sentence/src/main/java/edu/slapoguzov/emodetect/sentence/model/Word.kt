package edu.slapoguzov.emodetect.sentence.model

data class Word(
        val form: String,
        val lemma: String?,
        val partOfSpeech: PartOfSpeech,
        val popularity: Double,
        val characteristics: List<Characteristic>,
        val position: Int,
        override val valence: Double
) : Valenced