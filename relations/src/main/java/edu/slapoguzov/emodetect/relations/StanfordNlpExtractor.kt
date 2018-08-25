package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.relations.model.connl.ConnlSentence
import edu.stanford.nlp.international.russian.process.RussianProcessor
import java.io.BufferedWriter
import java.io.StringWriter

class StanfordNlpExtractor(
        parserModelPath: String,
        taggerPath: String,
        mfPath: String,
        dictPath: String
) : RelationsExtractor {
    private val russianProcessor = RussianProcessor(parserModelPath, taggerPath, mfPath, dictPath)
    private val connlReader = ConnlReader()

    private fun extract(text: List<String>): List<ConnlSentence> {
        val proccesText = russianProcessor.proccesText(text)
        return proccesText.map {
            val stringWriter = StringWriter()
            val bufferedWriter = BufferedWriter(stringWriter)
            russianProcessor.conlluPrint(it, bufferedWriter)
            connlReader.read(stringWriter.toString())
        }
    }

    override fun extract(text: String): ConnlSentence {
        return extract(listOf(text)).first()
    }
}