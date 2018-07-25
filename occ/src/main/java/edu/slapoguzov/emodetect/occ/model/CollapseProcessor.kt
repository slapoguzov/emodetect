package edu.slapoguzov.emodetect.occ.model

import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType
import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType.*
import mu.KLogging

internal class CollapseProcessor {

    companion object : KLogging() {
        val rules = setOf(
                setOf(HOPE, SATISFACTION) to SATISFACTION,
                setOf(FEAR, FEARS_CONFIRMED) to FEARS_CONFIRMED,
                setOf(PRIDE, GRATIFICATION) to GRATIFICATION,
                setOf(SHAME, REMORSE) to REMORSE,
                setOf(ADMIRATION, GRATITUDE) to GRATITUDE,
                setOf(REPROACH, ANGER) to ANGER,
                setOf(GRATITUDE, GRATIFICATION) to GRATITUDE,
                setOf(REMORSE, ANGER) to ANGER

        )
    }

    fun process(emotions: Set<EmotionType>): Set<EmotionType> {
        return _process(emotions.toMutableSet()).toSet()
    }

    private tailrec fun _process(emotions: MutableSet<EmotionType>): MutableSet<EmotionType> {
        val result = emotions.toMutableSet()
        val rule = rules.find { result.containsAll(it.first) }
                ?: return result
        logger.info { "apply rule: ${rule.first} to ${rule.second}" }
        result.removeAll(rule.first)
        result.add(rule.second)
        return _process(result)
    }
}