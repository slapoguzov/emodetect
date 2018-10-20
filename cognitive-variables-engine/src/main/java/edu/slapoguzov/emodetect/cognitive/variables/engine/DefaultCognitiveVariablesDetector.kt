package edu.slapoguzov.emodetect.cognitive.variables.engine

import edu.slapoguzov.emodetect.cognitive.variables.engine.detectors.*
import edu.slapoguzov.emodetect.occ.model.variables.CognitiveVariable
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.statistics.StatisticsComponent

class DefaultCognitiveVariablesDetector : CognitiveVariablesDetector {

    private val statisticsComponent = StatisticsComponent()

    override fun detect(sentence: Sentence): Set<CognitiveVariable> {
        return detectors.mapNotNull { it.detect(sentence) }.toSet()
    }

    private val detectors = listOf(
            AgentFondnessDetector(),
            ObjectFondnessDetector(),
            DirectionOfEmotionDetector(),
            EffortOfActionDetector(),
            EventDeservingnessDetector(),
            EventFamiliarityDetector(statisticsComponent)
    )
}