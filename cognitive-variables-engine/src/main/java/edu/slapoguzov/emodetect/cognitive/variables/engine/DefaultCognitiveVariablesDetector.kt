package edu.slapoguzov.emodetect.cognitive.variables.engine

import edu.slapoguzov.emodetect.cognitive.variables.engine.detectors.*
import edu.slapoguzov.emodetect.occ.model.variables.CognitiveVariable
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import mu.KLogging

class DefaultCognitiveVariablesDetector : CognitiveVariablesDetector {

    private val statisticsComponent = StatisticsComponent()

    override fun detect(sentence: Sentence): Set<CognitiveVariable> {
        val variables = detectors.mapNotNull { it.detect(sentence) }.toSet()
        val state = variables.joinToString { "${it.javaClass.simpleName}=$it" }
        logger.info { "cognitiveVariables: $state" }
        return variables
    }

    private val detectors = listOf(
            AgentFondnessDetector(),
            ObjectFondnessDetector(),
            DirectionOfEmotionDetector(),
            EffortOfActionDetector(),
            EventDeservingnessDetector(),
            EventFamiliarityDetector(statisticsComponent),
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