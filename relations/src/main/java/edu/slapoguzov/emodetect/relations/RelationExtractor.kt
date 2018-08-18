package edu.slapoguzov.emodetect.relations

import edu.stanford.nlp.international.russian.process.RussianProcessor
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.semgraph.SemanticGraph
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations
import edu.stanford.nlp.semgraph.SemanticGraphEdge
import java.io.BufferedWriter
import java.io.StringWriter
import java.io.Writer

class RelationExtractor(
        parserModelPath: String,
        taggerPath: String,
        mfPath: String,
        dictPath: String
) {
    private val russianProcessor = RussianProcessor(parserModelPath, taggerPath, mfPath, dictPath)

    fun extract(text: List<String>): List<Annotation> {
        val proccesText = russianProcessor.proccesText(text)
        proccesText.forEach { russianProcessor.conlluPrint(it, System.out.bufferedWriter()) }
        return proccesText
    }

    fun extract(text: String): Annotation {
        return extract(listOf(text)).first()
    }

    fun extractSemanticGraph(text: String): List<SemanticGraph> {
        val document = extract(text)
        val sentences = document.get(SentencesAnnotation::class.java)
        return sentences.map { sentence ->
            sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation::class.java)
        }
    }
}