package edu.slapoguzov.emodetect.occ.model.variables

/**
 * In order to initiate further analysis to sense emotions
 * or decide the sentence(s) as expressing a neutral emotion.
 *
 * I go = FALSE
 * I go to gym everyday = TRUE
 *
 * Thus we call this variable the trigger for a further emotion analysis process.
 */
enum class ValencedReaction : EventBasedVariable {
    TRUE,
    FALSE
}