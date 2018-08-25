package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.morpho.mystem.MyStemFactory
import edu.slapoguzov.emodetect.relations.MorphoProcessor
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.statistics.StatisticsComponent


object Application {
    private val relationExtractor = RemoteSyntaxNetExtractor()

    private val relationProcessor = RelationProcessor()
    private val statisticsComponent = StatisticsComponent()
    private val collectingProcessor = CollectingProcessor(relationExtractor, relationProcessor, statisticsComponent)

    fun run() {
        val text = "Внезапно я нашел пьяным его в самолете ."
        val sentence = collectingProcessor.process(text)
        println(sentence)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

