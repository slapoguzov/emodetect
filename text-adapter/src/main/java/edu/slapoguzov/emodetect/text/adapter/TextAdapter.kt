package edu.slapoguzov.emodetect.text.adapter

import edu.slapoguzov.emodetect.cognitive.variables.engine.DefaultCognitiveVariablesDetector
import edu.slapoguzov.emodetect.occ.model.EmotionsDetector
import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.sentence.Application
import edu.slapoguzov.emodetect.sentence.CollectingProcessor
import edu.slapoguzov.emodetect.sentence.RelationProcessor
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import mu.KLogging

class TextAdapter {

    private val relationExtractor = RemoteSyntaxNetExtractor()

    private val relationProcessor = RelationProcessor()
    private val statisticsComponent = StatisticsComponent()
    private val collectingProcessor = CollectingProcessor(relationExtractor, relationProcessor, statisticsComponent)

    private val cognitiveVariablesDetector = DefaultCognitiveVariablesDetector()
    private val emotionsDetector = EmotionsDetector()


    fun detectEmotions(text: String): Set<EmotionType> {
        logger.info { "source text: $text" }
        val sentence = collectingProcessor.process(text)
        logger.info { "sentence: $sentence" }
        val cognitiveVariables = cognitiveVariablesDetector.detect(sentence)
        return emotionsDetector.detect(cognitiveVariables)
    }

    private companion object : KLogging()
}