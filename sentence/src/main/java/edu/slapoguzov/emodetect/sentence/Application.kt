package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.sentence.mystem.MyStemFactory
import ru.stachek66.nlp.mystem.holding.Request


object Application {
    private val parserModelPath = getPathToFile("nndep.rus.model90.9_88.6.txt.gz")
    private val taggerPath = getPathToFile("russian-ud-pos.tagger")
    private val mfPath = getPathToFile("russian-ud-mf.tagger")
    private val dictPath = getPathToFile("dict.tsv")
    private val myStemPath = getPathToFile("mystem-3.1.exe")

    private val relationExtractor = RelationExtractor(parserModelPath, taggerPath, mfPath, dictPath)
    private val myStemFactory = MyStemFactory(myStemPath)
    private val myStem = myStemFactory.getMyStem()
    private val morphoProcessor = MorphoProcessor(myStem)
    private val collectingProcessor = CollectingProcessor(morphoProcessor, relationExtractor)

    fun run() {
        val text = "Внезапно я нашел его в самолете"
        val sentence = collectingProcessor.process(text)
        println(sentence)
    }

    private fun getPathToFile(name: String): String {
        return this::class.java.classLoader.getResource(name).toURI().path
    }
}

fun main(args: Array<String>) {
    Application.run()
}

