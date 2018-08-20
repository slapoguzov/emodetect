package edu.slapoguzov.emodetect.statistics.entity

import com.opencsv.bean.CsvBindByPosition

class RussentilexRow {
    @CsvBindByPosition(required = true, position = 0)
    lateinit var word: String

    @CsvBindByPosition(required = true, position = 3)
    lateinit var _valency: String

    val valency: Valence by lazy {
        when(_valency) {
            "negative" -> Valence.NEGATIVE
            "positive" -> Valence.POSITIVE
            else ->Valence.NEUTRAL
        }
    }
}