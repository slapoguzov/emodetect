package edu.slapoguzov.emodetect.occ.model.variables

/**
 * It is depend on whether the agent that experiences some emotion
 * is reacting to consequences of events for itself or to consequences for others.
 *
 * This value for DirectionOfEmotion is set as ‘OTHER’ if the object or the predicate
 * of the event described in the text is a person (e.g., John) or a personal pronoun (I, he, she).
 *
 * Example:
 *  - "Mary congratulates John for having won a prize." The value is `OTHER`.
 *  - "It is a very interesting idea." The value is `SELF`.
 *  - "Susan won the million dollar lottery."  The value is `SELF`.
 */
enum class DirectionOfEmotion : AgentBasedVariable {
    SELF,
    OTHER
}