package edu.slapoguzov.emodetect.cognitive.variables.engine

import edu.slapoguzov.emodetect.cognitive.variables.engine.detectors.*
import edu.slapoguzov.emodetect.occ.model.variables.CognitiveVariable
import edu.slapoguzov.emodetect.occ.model.variables.DirectionOfEmotion
import edu.slapoguzov.emodetect.occ.model.variables.DirectionOfEmotion.*
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import mu.KLogging

class DefaultCognitiveVariablesDetector : CognitiveVariablesDetector {

    private val statisticsComponent = StatisticsComponent()

    override fun detect(sentence: Sentence): Set<CognitiveVariable> {
        //TODO: многие эмоции не зависят от направления: см тесты
        val directions = listOf(OTHER, SELF)
        val variables = (detectors.mapNotNull { it.detect(sentence) }.toMutableList() + directions).toSet()
        val state = variables.joinToString("\n") { "${it.javaClass.simpleName} = $it" }
        logger.info { "cognitiveVariables: \n$state" }
        return variables
    }

    private val detectors = listOf(
            AgentFondnessDetector(),
            ObjectFondnessDetector(),
            DirectionOfEmotionDetector(),
            EffortOfActionDetector(),
            EventDeservingnessDetector(),
            EventFamiliarityDetector(),
            ExpectedDeviationDetector(statisticsComponent),
            ObjectAppealingDetector(),
            OtherPresumptionDetector(),
            ProspectDetector(statisticsComponent),
            SelfAppraisalDetector(),
            SelfPresumptionDetector(),
            SelfReactionDetector(),
            StatusDetector(),
            UnexpectednessDetector(),
            ValencedReactionDetector()
    )

    private companion object : KLogging()
}