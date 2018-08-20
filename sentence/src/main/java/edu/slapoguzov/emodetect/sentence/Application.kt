package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.morpho.mystem.MyStemFactory
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.statistics.StatisticsComponent


object Application {
    private val relationExtractor = RemoteSyntaxNetExtractor()
    private val myStem = MyStemFactory().getMyStem()

    private val morphoProcessor = MorphoProcessor(myStem)
    private val relationProcessor = RelationProcessor()
    private val statisticsComponent = StatisticsComponent()
    private val collectingProcessor = CollectingProcessor(morphoProcessor, relationExtractor, relationProcessor, statisticsComponent)

    fun run() {
        val text = "Внезапно я нашел его в самолете ."
        val sentence = collectingProcessor.process(text)
        println(sentence)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

