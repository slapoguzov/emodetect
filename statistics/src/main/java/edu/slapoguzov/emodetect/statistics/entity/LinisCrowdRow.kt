package edu.slapoguzov.emodetect.statistics.entity

import com.opencsv.bean.CsvBindByPosition

class LinisCrowdRow {
    @CsvBindByPosition(required = true, position = 0)
    lateinit var word: String

    @CsvBindByPosition(required = true, position = 1)
    var _valency: Long = 0

    val valency: Valence by lazy {
        when(_valency) {
            -1L -> Valence.NEGATIVE
            1L -> Valence.POSITIVE
            else ->Valence.NEUTRAL
        }
    }


}