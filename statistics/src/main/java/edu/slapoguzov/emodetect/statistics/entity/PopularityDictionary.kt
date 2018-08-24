package edu.slapoguzov.emodetect.statistics.entity

data class PopularityDictionary(
    val words: Map<String, WordPopularity>,
    val totalOfUse: Long
)