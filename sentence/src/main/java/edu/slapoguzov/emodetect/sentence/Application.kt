package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.morpho.mystem.MyStemFactory
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor


object Application {
    private val relationExtractor = RemoteSyntaxNetExtractor()
    private val myStem = MyStemFactory().getMyStem()

    private val morphoProcessor = MorphoProcessor(myStem)
    private val relationProcessor = RelationProcessor()
    private val collectingProcessor = CollectingProcessor(morphoProcessor, relationExtractor, relationProcessor)

    fun run() {
        val text = "Внезапно я нашел его в самолете ."
        val sentence = collectingProcessor.process(text)
        println(sentence)
    }
}

fun main(args: Array<String>) {
    Application.run()
}

