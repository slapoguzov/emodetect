package edu.slapoguzov.emodetect.occ.model.variables

/**
 * According to the OCC model, the prospect of an event involves a conscious expectation
 * that it will occur in the future, and the value for the variable prospect (pros)
 * can be either positive or negative.
 *
 * According to the equation, if a verb has more positive senses than negative senses,
 * the verb is more close to optimism and has positive prospective value;
 * otherwise, the verb gets a negative prospective value.
 *
 * Examples:
 *  - "admit to university". POSITIVE
 *  - "kill innocent people". NEGATIVE
 *  - "do it". NEUTRAL
 */
enum class Prospect : EventBasedVariable {
    POSITIVE,
    NEGATIVE,
    NEUTRAL
}