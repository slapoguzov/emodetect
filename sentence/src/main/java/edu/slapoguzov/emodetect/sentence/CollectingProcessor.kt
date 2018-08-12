package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.PartOfSpeech
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.sentence.model.Word
import edu.slapoguzov.emodetect.sentence.mystem.model.Grammem
import edu.slapoguzov.emodetect.sentence.mystem.model.StemUnit
import edu.stanford.nlp.ling.IndexedWord
import edu.stanford.nlp.pipeline.CoreNLPProtos
import edu.stanford.nlp.trees.EnglishGrammaticalRelations
import edu.stanford.nlp.trees.GrammaticalRelation
import edu.slapoguzov.emodetect.sentence.toPartOfSpeach

class CollectingProcessor(
        private val morphoProcessor: MorphoProcessor,
        private val relationExtractor: RelationExtractor
) {

    fun process(text: String): Sentence {
        val morphoUnits = morphoProcessor.process(text)
        val semanticGraph = relationExtractor.extractSemanticGraph(text)
        semanticGraph.flatMap {
            it.edgeIterable().map {
                val src = WordData(it.source, morphoUnits[it.source.index()]!!)
                val target = WordData(it.target, morphoUnits[it.target.index()]!!)
                processRelation(src, target, it.relation)
            }
        }
        return Sentence(emptyList(), 0.0)
    }

    fun processRelation(src: WordData, target: WordData, rel: GrammaticalRelation) {
        when (rel) {
            EnglishGrammaticalRelations.NOMINAL_SUBJECT -> listOf(
                    Word(
                            text = src.indexedWord.originalText(),
                            partOfSpeech = src.stemUnit.partOfSpeach!!.toPartOfSpeach(),
                            popularity = 0.0,
                            position = src.indexedWord.index(),
                            valence = 0.0,
                            characteristics = listOf(Characteristic.IS_AGENT)
                    ),
                    Word(
                            text = target.indexedWord.originalText(),
                            partOfSpeech = target.stemUnit.partOfSpeach!!.toPartOfSpeach(),
                            popularity = 0.0,
                            position = src.indexedWord.index(),
                            valence = 0.0,
                            characteristics = listOf(Characteristic.IS_ACTION)
                    )
            )
        }
    }


    data class WordData(
            val indexedWord: IndexedWord,
            val stemUnit: StemUnit
    )
}