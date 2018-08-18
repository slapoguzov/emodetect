package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.relations.RelationExtractor
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.sentence.model.Word
import edu.slapoguzov.emodetect.sentence.mystem.model.StemUnit
import edu.stanford.nlp.ling.IndexedWord
import edu.stanford.nlp.naturalli.ClauseSplitter
import edu.stanford.nlp.naturalli.ClauseSplitterSearchProblem
import edu.stanford.nlp.trees.EnglishGrammaticalRelations
import edu.stanford.nlp.trees.GrammaticalRelation
import mu.KLogging

class CollectingProcessor(
        private val morphoProcessor: MorphoProcessor,
        private val relationExtractor: RelationExtractor,
        private val relationProcessor: RelationProcessor
) {

    fun process(text: String): Sentence {
        val morphoUnits = morphoProcessor.process(text)
        val semanticGraph = relationExtractor.extractSemanticGraph(text)
        semanticGraph.flatMap {
            it.edgeIterable().map {
                val relationChars = relationProcessor.process(RelationProcessorParameters(it.relation))

            }
        }
        return Sentence(emptyList(), 0.0)
    }

    private companion object : KLogging()
}