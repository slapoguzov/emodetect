package edu.slapoguzov.emodetect.backend

import edu.slapoguzov.emodetect.backend.service.EmotionService
import edu.slapoguzov.emodetect.cognitive.variables.engine.DefaultCognitiveVariablesDetector
import edu.slapoguzov.emodetect.occ.model.EmotionsDetector
import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType
import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType.*
import edu.slapoguzov.emodetect.occ.model.variables.*
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.sentence.CollectingProcessor
import edu.slapoguzov.emodetect.sentence.RelationProcessor
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import jdk.nashorn.internal.ir.annotations.Ignore
import mu.KLogging
import org.junit.jupiter.api.fail
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.MethodSource

@Ignore
class EmotionsIT {
    private val relationExtractor = RemoteSyntaxNetExtractor(syntaxNetHost, syntaxNetPort, mystemPath)

    private val relationProcessor = RelationProcessor()
    private val statisticsComponent = StatisticsComponent()
    private val collectingProcessor = CollectingProcessor(relationExtractor, relationProcessor, statisticsComponent)

    private val cognitiveVariablesDetector = DefaultCognitiveVariablesDetector()
    private val emotionService = EmotionService(collectingProcessor, cognitiveVariablesDetector, EmotionsDetector())

    companion object : KLogging() {
        const val syntaxNetHost = "localhost"
        const val syntaxNetPort = 8111
        const val mystemPath = "C:\\MyStem\\mystem-3.1.exe"
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/test_data_set"] , delimiter = '\t')
    fun test(expectedEmotion: String, text: String) {
        logger.info { "sentence: $text" }
        val actualEmotions = emotionService.detectEmotions(text).map { it.toTest() }.toSet()
        if (actualEmotions.contains(expectedEmotion)) return
        fail("Test for text: $text failed. Expected emotion: \n$expectedEmotion, but was:\n$actualEmotions")
    }


    fun EmotionType.toTest(): String {
        return when(this){
            SHOCK, FEAR, FEARS_CONFIRMED -> "FEAR"
            JOY, HAPPY_FOR, SATISFACTION, ADMIRATION, PRIDE -> "JOY"
            ANGER, REPROACH -> "ANGER"
            DISTRESS, SORRY_FOR, DISAPPOINTMENT, SHAME -> "SADNESS"
            HATE -> "DISGUST"
            else -> this.name
        }
    }

}