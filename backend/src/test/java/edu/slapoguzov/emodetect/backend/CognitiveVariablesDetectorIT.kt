package edu.slapoguzov.emodetect.backend

import edu.slapoguzov.emodetect.cognitive.variables.engine.DefaultCognitiveVariablesDetector
import edu.slapoguzov.emodetect.occ.model.variables.*
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.sentence.CollectingProcessor
import edu.slapoguzov.emodetect.sentence.RelationProcessor
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import jdk.nashorn.internal.ir.annotations.Ignore
import mu.KLogging
import org.junit.jupiter.api.fail
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@Ignore
class CognitiveVariablesDetectorIT {
    private val relationExtractor = RemoteSyntaxNetExtractor(syntaxNetHost, syntaxNetPort, mystemPath)

    private val relationProcessor = RelationProcessor()
    private val statisticsComponent = StatisticsComponent()
    private val collectingProcessor = CollectingProcessor(relationExtractor, relationProcessor, statisticsComponent)

    private val cognitiveVariablesDetector = DefaultCognitiveVariablesDetector()

    companion object : KLogging() {
        @JvmStatic
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
        const val syntaxNetHost = "localhost"
        const val syntaxNetPort = 8111
        const val mystemPath = "C:\\MyStem\\mystem-3.1.exe"
    }

    @ParameterizedTest
    @MethodSource("data")
    fun test(text: String, expectedVariables: Set<CognitiveVariable>) {
        val expectedClasses = expectedVariables.map { it.javaClass }
        val sentence = collectingProcessor.process(text)
        logger.info { "sentence: $sentence" }
        val actualVariables = cognitiveVariablesDetector.detect(sentence).filter { it.javaClass in expectedClasses }
        if(actualVariables.containsAll(expectedVariables)) return
        fail("Test for text: $text failed. Expected variables: \n$expectedVariables, but was:\n$actualVariables")
    }

}