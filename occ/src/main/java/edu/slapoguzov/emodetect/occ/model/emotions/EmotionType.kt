package edu.slapoguzov.emodetect.occ.model.emotions

import edu.slapoguzov.emodetect.occ.model.variables.*

enum class EmotionType(
        val cognitiveVariables: Set<CognitiveVariable>
) {
    JOY(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    SelfPresumption.DESIRABLE
            )
    ),
    HAPPY_FOR(
            cognitiveVariables = setOf(
                    SelfReaction.PLEASED,
                    OtherPresumption.DESIRABLE,
                    AgentFondness.LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    DISTRESS(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    SelfPresumption.UNDESIRABLE,
                    DirectionOfEmotion.SELF
            )
    ),
    SORRY_FOR(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    OtherPresumption.UNDESIRABLE,
                    AgentFondness.LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    RESENTMENT(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    OtherPresumption.DESIRABLE,
                    AgentFondness.NOT_LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    GLOATING(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    OtherPresumption.UNDESIRABLE,
                    AgentFondness.NOT_LIKED,
                    DirectionOfEmotion.OTHER
            )
    ),
    HOPE(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    Prospect.POSITIVE,
                    SelfPresumption.DESIRABLE,
                    Status.UNCONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    FEAR(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    Prospect.NEGATIVE,
                    SelfPresumption.UNDESIRABLE,
                    Status.UNCONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    SATISFACTION(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    Prospect.POSITIVE,
                    SelfPresumption.DESIRABLE,
                    Status.CONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    FEARS_CONFIRMED(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    Prospect.NEGATIVE,
                    SelfPresumption.UNDESIRABLE,
                    Status.CONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    RELIEF(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    Prospect.NEGATIVE,
                    SelfPresumption.UNDESIRABLE,
                    Status.DISCONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    DISAPPOINTMENT(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    Prospect.POSITIVE,
                    SelfPresumption.DESIRABLE,
                    Status.DISCONFIRMED,
                    DirectionOfEmotion.SELF
            )
    ),
    PRIDE(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    SelfAppraisal.PRAISEWORTHY,
                    SelfPresumption.DESIRABLE,
                    DirectionOfEmotion.SELF
            )
    ),
    SHAME(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    SelfAppraisal.BLAMEWORTHY,
                    SelfPresumption.DESIRABLE,
                    DirectionOfEmotion.SELF
            )
    ),
    ADMIRATION(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.PLEASED,
                    SelfAppraisal.PRAISEWORTHY,
                    OtherPresumption.DESIRABLE,
                    DirectionOfEmotion.OTHER
            )
    ),
    REPROACH(
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfReaction.DISPLEASED,
                    SelfAppraisal.BLAMEWORTHY,
                    OtherPresumption.UNDESIRABLE,
                    DirectionOfEmotion.OTHER
            )
    ),
    LOVE(
            cognitiveVariables = setOf(
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
            cognitiveVariables = setOf(
                    ValencedReaction.TRUE,
                    SelfPresumption.UNDESIRABLE,
                    SelfReaction.DISPLEASED,
                    ObjectFondness.NOT_LIKED,
                    ObjectAppealing.NOT_ATTRACTIVE,
                    // TODO: event valency = negative
                    DirectionOfEmotion.OTHER
            )
    ),
    GRATIFICATION(
            cognitiveVariables = JOY.cognitiveVariables + PRIDE.cognitiveVariables
    ),
    REMORSE(
            cognitiveVariables = DISTRESS.cognitiveVariables + SHAME.cognitiveVariables
    ),
    GRATITUDE(
            cognitiveVariables = JOY.cognitiveVariables + ADMIRATION.cognitiveVariables
    ),
    ANGER(
            cognitiveVariables = DISTRESS.cognitiveVariables + REPROACH.cognitiveVariables
    ),
    SHOCK(
            cognitiveVariables = DISTRESS.cognitiveVariables + Unexpectedness.TRUE
    ),
    SURPRISE(
            cognitiveVariables = JOY.cognitiveVariables + Unexpectedness.TRUE
    )
}