package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.relations.RelationExtractorFactory
import edu.slapoguzov.emodetect.morpho.mystem.MyStemFactory


object Application {
    private val relationExtractor = RelationExtractorFactory().getInstance()
    private val myStem = MyStemFactory().getMyStem()

    private val morphoProcessor = MorphoProcessor(myStem)
    private val relationProcessor = RelationProcessor()
    private val collectingProcessor = CollectingProcessor(morphoProcessor, relationExtractor, relationProcessor)

    fun run() {
        val text = "Внезапно я нашел его в самолете, но он был пьян"
        val sentence = collectingProcessor.process(text)
        println(sentence)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

