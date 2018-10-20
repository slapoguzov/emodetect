package edu.slapoguzov.emodetect.statistics

import edu.slapoguzov.emodetect.statistics.utils.PopularityDictionaryParser
import edu.slapoguzov.emodetect.statistics.utils.ValencyDictionariesMerge
import edu.slapoguzov.emodetect.statistics.utils.WordPopularityExtractor

object Application {
    fun run() {
        val wordPopularityExtractor = WordPopularityExtractor()
        val popularities = PopularityDictionaryParser.parse()
        println(popularities)
    }
}

fun main(args: Array<String>) {
    Application.run()
}