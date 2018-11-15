package edu.slapoguzov.emodetect.backend

import edu.slapoguzov.emodetect.backend.service.EmotionService
import edu.slapoguzov.emodetect.cognitive.variables.engine.DefaultCognitiveVariablesDetector
import edu.slapoguzov.emodetect.occ.model.EmotionsDetector
import edu.slapoguzov.emodetect.occ.model.variables.*
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.sentence.CollectingProcessor
import edu.slapoguzov.emodetect.sentence.RelationProcessor
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import mu.KLogging
import org.junit.Assert.fail
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@Ignore
@RunWith(Parameterized::class)
class CognitiveVariablesDetectorIT(
        val text: String,
        val expectedVariables: Set<CognitiveVariable>
) {
    private val relationExtractor = RemoteSyntaxNetExtractor()

    private val relationProcessor = RelationProcessor()
    private val statisticsComponent = StatisticsComponent()
    private val collectingProcessor = CollectingProcessor(relationExtractor, relationProcessor, statisticsComponent)

    private val cognitiveVariablesDetector = DefaultCognitiveVariablesDetector()

    companion object : KLogging() {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data(): Collection<Array<out Any?>> {
            return listOf(
                    arrayOf("Она любит страшные фильмы", setOf(SelfPresumption.DESIRABLE, SelfReaction.PLEASED)),
                    arrayOf("Гитлер убил тысячу невинных людей", setOf(SelfPresumption.UNDESIRABLE, SelfReaction.DISPLEASED)),

                    arrayOf("Преподавателя наградили премией", setOf(OtherPresumption.DESIRABLE)),
                    arrayOf("Мать наказала сына", setOf(OtherPresumption.UNDESIRABLE)),
                    arrayOf("Бандит полюбил красавицу", setOf(OtherPresumption.UNDESIRABLE)),

                    arrayOf("Мария поздравила Колю с призом", setOf(DirectionOfEmotion.OTHER)),
                    arrayOf("Павел выйграл миллион долларов", setOf(DirectionOfEmotion.SELF)),

                    arrayOf("Он победил болезнь", setOf(Prospect.POSITIVE)),
                    arrayOf("Человечество истребило миллионы животных", setOf(Prospect.NEGATIVE)),
                    arrayOf("Он гуляет", setOf(Prospect.NEUTRAL))

            )
        }
    }

    @Test
    fun test() {
        val expectedClasses = expectedVariables.map { it.javaClass }
        val sentence = collectingProcessor.process(text)
        logger.info { "sentence: $sentence" }
        val actualVariables = cognitiveVariablesDetector.detect(sentence).filter { it.javaClass in expectedClasses }
        if(actualVariables.containsAll(expectedVariables)) return
        fail("Test for text: $text failed. Expected variables: \n$expectedVariables, but was:\n$actualVariables")
    }

}