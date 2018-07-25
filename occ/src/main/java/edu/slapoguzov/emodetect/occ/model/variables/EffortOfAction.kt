package edu.slapoguzov.emodetect.occ.model.variables

/**
 * According to the OCC model, when effort is a factor,
 * the greater the effort invested, the more intense the emotion.
 *
 * If an action is qualified with an adverb (except the exceptional adverbs
 * such as hardly) or target object qualified with an adjective without a negation,
 * the value for EffortOfAction is set ‘OBVIOUS’ otherwise ‘NOT_OBVIOUS’
 *
 * He worked very hard. OBVIOUS
 * I am looking for a quiet place. OBVIOUS
 */
enum class EffortOfAction : IntensityVariable {
    OBVIOUS,
    NOT_OBVIOUS
}