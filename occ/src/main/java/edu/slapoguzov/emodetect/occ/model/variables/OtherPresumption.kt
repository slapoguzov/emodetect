package edu.slapoguzov.emodetect.occ.model.variables

/**
 * Assessing the event from the perspective of the agent pertaining to an event being assessed.
 *
 * For the sentence ‘A terrorist escaped from the jail’ the value is presumably ‘DESIRABLE’
 * for the agent ‘terrorist’ because the contextual valence of the event ‘escape from jail’ is negative
 * which is associated with a negative valenced actor ‘terrorist’. Negative actor usually desires to do negative things
 *
 * If a positive valenced event is associated with a positive valenced agent, OtherPresumption is set to ‘DESIRABLE’.
 * If a negative valenced event is associated with a positive valenced agent, OtherPresumption is set to ‘UNDESIRABLE’.
 * If a positive valenced event is associated with a negative valenced agent, OtherPresumption is set ‘UNDESIRABLE’.
 */
enum class OtherPresumption : EventBasedVariable {
    DESIRABLE,
    UNDESIRABLE
}