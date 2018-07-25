package edu.slapoguzov.emodetect.occ.model.variables

/**
 * DISCONFIRMED = PAST + NO + NEGATIVE VERB
 * CONFIRMED = PAST + [ NO ] + POSITIVE VERB
 * CONFIRMED = PAST + NEGATIVE VERB
 * UNCONFIRMED = PRESENT\FUTURE
 *
 * Examples:
 *  - "I am trying to solve it. He will come. The team may not play." UNCONFIRMED
 *  - "I succeeded. He didn’t come. The team played well." CONFIRMED
 *  - "The hostage was killed. The team defeated its opponent" CONFIRMED
 *  - "I didn’t fail. He didn’t hit the boy. The team couldn’t defeat its opponent. DISCONFIRMED
 */
enum class Status : EventBasedVariable {
    UNCONFIRMED,
    CONFIRMED,
    DISCONFIRMED
}