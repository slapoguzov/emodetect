package edu.slapoguzov.emodetect.occ.model.variables

/**
 * In order to assign a value to oa for an object, we consider two scores,
 * namely, ‘object valence’ and ‘familiarity valence’.
 *
 * Basically the ‘familiarity valence’ indicates how common or uncommon
 * the input object is with respect to the commonsense knowledge-base corpus.
 *
 * Uncommon + Positive = ATTRACTIVE
 * Common + Negative = NOT_ATTRACTIVE
 *
 * diamond ring. ATTRACTIVE
 * thief NOT_ATTRACTIVE
 * restaurant NEUTRAL
 */
enum class ObjectAppealing : ObjectBasedVariable {
    ATTRACTIVE,
    NOT_ATTRACTIVE,
    NEUTRAL
}