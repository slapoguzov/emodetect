package edu.slapoguzov.emodetect.occ.model

import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType
import edu.slapoguzov.emodetect.occ.model.variables.CognitiveVariable
import mu.KLogging

class EmotionsDetector {
    companion object : KLogging() {
        private val collapseProcessor = CollapseProcessor()
    }

    fun detect(cognitiveVariables: Set<CognitiveVariable>): Set<EmotionType> {
        val emotionTypes = EmotionType.values().filter {
            cognitiveVariables.containsAll(it.cognitiveVariables)
        }.toSet()
        logger.info { "dirty emotionTypes: $emotionTypes" }
        val clearEmotionTypes = collapseProcessor.process(emotionTypes)
        logger.info { "clear emotionTypes: $clearEmotionTypes" }
        return emotionTypes
    }
}