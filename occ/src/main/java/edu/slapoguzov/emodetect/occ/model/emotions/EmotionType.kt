package edu.slapoguzov.emodetect.occ.model.emotions

import edu.slapoguzov.emodetect.occ.model.variables.*

enum class EmotionType(
        val cognitiveVariables: List<CognitiveVariable>
) {
    JOY(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    SelfPresumption.DESIRABLE
            )
    ),
    HAPPY_FOR(
            cognitiveVariables = listOf(
                    SelfReaction.PLEASED,
                    OtherPresumtion.DESIRABLE,
                    AgentFondness.LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    DISTRESS(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    SelfPresumption.UNDESIRABLE,
                    DirectionOfEmotion.SELF
            )
    ),
    SORRY_FOR(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    OtherPresumtion.UNDESIRABLE,
                    AgentFondness.LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    RESENTMENT(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    OtherPresumtion.DESIRABLE,
                    AgentFondness.NOT_LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    GLOATING(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    OtherPresumtion.UNDESIRABLE,
                    AgentFondness.NOT_LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    HOPE(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    Prospect.POSITIVE,
                    SelfPresumption.DESIRABLE,
                    Status.UNCONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    FEAR(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    Prospect.POSITIVE,
                    SelfPresumption.DESIRABLE,
                    Status.UNCONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    SATISFACTION(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    Prospect.POSITIVE,
                    SelfPresumption.DESIRABLE,
                    Status.CONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    FEARS_CONFIRMED(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    Prospect.NEGATIVE,
                    SelfPresumption.DESIRABLE,
                    Status.CONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    RELIEF(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    Prospect.NEGATIVE,
                    SelfPresumption.UNDESIRABLE,
                    Status.DISCONFIRMED, DirectionOfEmotion.SELF
            )
    ),
    DISAPPOINTMENT(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    Prospect.POSITIVE,
                    SelfPresumption.DESIRABLE,
                    Status.DISCONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    PRIDE(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    SelfAppraisal.PRAISEWORTHY,
                    SelfPresumption.DESIRABLE,
                    DirectionOfEmotion.SELF
            )
    ),
    SHAME(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    SelfAppraisal.BLAMEWORTHY,
                    SelfPresumption.DESIRABLE,
                    DirectionOfEmotion.SELF
            )
    ),
    ADMIRATION(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    SelfAppraisal.PRAISEWORTHY,
                    OtherPresumtion.DESIRABLE,
                    DirectionOfEmotion.OTHER
            )
    ),
    REPROACH(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    SelfAppraisal.BLAMEWORTHY,
                    OtherPresumtion.UNDESIRABLE,
                    DirectionOfEmotion.OTHER
            )
    ),
    LOVE(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfPresumption.DESIRABLE,
                    SelfReaction.PLEASED,
                    ObjectFondness.LIKED,
                    ObjectAppealing.ATTRACTIVE,
                    // TODO: event valency = positive
                    DirectionOfEmotion.OTHER
            )
    ),
    HATE(
            cognitiveVariables = listOf(
                    ValencedReaction.TRUE,
                    SelfPresumption.UNDESIRABLE,
                    SelfReaction.DISPLEASED,
                    ObjectFondness.NOT_LIKED,
                    ObjectAppealing.NOT_ATTRACTIVE,
                    // TODO: event valency = negative
                    DirectionOfEmotion.OTHER
            )
    )
}