package edu.slapoguzov.emodetect.occ.model

import edu.slapoguzov.emodetect.occ.model.variables.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.BeforeClass



class EmotionsDetectorTest {

    private val emotionsDetector = EmotionsDetector()

    /**
     * Test for sentence:
     * “Я не видел Ивана несколько часов; Я думал, он может пропустить рейс, но внезапно я нашел его в самолете."
     *  e1: Я не видел Ивана несколько часов
     *  e2: Я думал, он может пропустить рейс
     *  e3: но внезапно я нашел его в самолете
     */
    @Test
    fun test() {
        val e1: Set<CognitiveVariable> = setOf(
                ObjectFondness.LIKED,
                DirectionOfEmotion.OTHER,
                ObjectAppealing.ATTRACTIVE,
                SelfReaction.DISPLEASED,
                SelfPresumption.UNDESIRABLE,
                OtherPresumption.UNDESIRABLE,
                Prospect.NEGATIVE,
                Status.CONFIRMED,
                Unexpectedness.FALSE,
                SelfAppraisal.BLAMEWORTHY,
                ValencedReaction.TRUE,
                EventDeservingness.LOW,
                EffortOfAction.NOT_OBVIOUS,
                ExpectedDeviation.LOW,
                EventFamiliarity.COMMON
        )
        val e2: Set<CognitiveVariable> = setOf(
                ObjectFondness.LIKED,
                AgentFondness.LIKED,
                DirectionOfEmotion.SELF,
                ObjectAppealing.NEUTRAL,
                SelfReaction.DISPLEASED,
                SelfPresumption.UNDESIRABLE,
                Prospect.NEGATIVE,
                Status.UNCONFIRMED,
                Unexpectedness.FALSE,
                SelfAppraisal.BLAMEWORTHY,
                ValencedReaction.TRUE,
                EventDeservingness.LOW,
                EffortOfAction.NOT_OBVIOUS,
                ExpectedDeviation.LOW,
                EventFamiliarity.UNCOMMON
        )
        val e3: Set<CognitiveVariable> = setOf(
                ObjectFondness.LIKED,
                AgentFondness.LIKED,
                DirectionOfEmotion.OTHER,
                ObjectAppealing.ATTRACTIVE,
                SelfReaction.PLEASED,
                SelfPresumption.DESIRABLE,
                OtherPresumption.DESIRABLE,
                Prospect.POSITIVE,
                Status.CONFIRMED,
                Unexpectedness.TRUE,
                SelfAppraisal.PRAISEWORTHY,
                ValencedReaction.TRUE,
                EventDeservingness.HIGH,
                EffortOfAction.OBVIOUS,
                ExpectedDeviation.LOW,
                EventFamiliarity.COMMON
        )

        val cognitiveVariables: Set<CognitiveVariable> = e1 + e2 + e3
        val actualResult = emotionsDetector.detect(e3)
        println(actualResult)
    }
}