package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.relations.RelationsExtractor
import edu.slapoguzov.emodetect.relations.StanfordNlpExtractor
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.model.Sentence
import mu.KLogging

class CollectingProcessor(
        private val morphoProcessor: MorphoProcessor,
        private val relationExtractor: RelationsExtractor,
        private val relationProcessor: RelationProcessor
) {

    fun process(text: String): Sentence {
        val morphoUnits = morphoProcessor.process(text)
        val relations = relationExtractor.extract(text)
        return Sentence(emptyList(), 0.0)
    }

    private companion object : KLogging()
}