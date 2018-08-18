package edu.slapoguzov.emodetect.sentence.entity

import edu.slapoguzov.emodetect.sentence.model.Characteristic

data class RelationProcessorResult(
        val srcCharacteristics: List<Characteristic>,
        val targetCharacteristics: List<Characteristic>
)