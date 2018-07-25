package edu.slapoguzov.emodetect.occ.model.variables

/**
 * These variable is assessed with respect to the events.
 *
 * For example, `I like romantic movies`  - DESIRABLE.
 *              `kill innocent civilians` - UNDESIRABLE
 *
 * But for the sentence ‘She likes horror movies’, the positive action ‘like’
 * is associated with a negative concept (i.e., horror movie), but event
 * is conveying positive sentiment because positive affect is being expressed
 * by the word ‘like’.
 */
enum class SelfPresumption : EventBasedVariable {
    DESIRABLE,
    UNDESIRABLE
}