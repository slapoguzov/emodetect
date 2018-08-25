package edu.slapoguzov.emodetect.statistics

import edu.slapoguzov.emodetect.statistics.utils.PopularityDictionaryParser
import edu.slapoguzov.emodetect.statistics.utils.ValencyDictionariesMerge

object Application {
    fun run() {
        val statisticsComponent = StatisticsComponent()
        println(statisticsComponent.getPopularity("гитлер"))
    }
}

fun main(args: Array<String>) {
    Application.run()
}