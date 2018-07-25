package edu.slapoguzov.emodetect.occ.model.variables

/**
 * The variable called expected deviation (edev) indicates the difference between
 * the event and its actor in terms of expectancy of the event being associated with the actor.
 *
 * The police caught the criminal finally. LOW
 * A student invented this theory. HIGH
 * The scientist invented the theory. LOW
 */
enum class ExpectedDeviation : IntensityVariable {
    HIGH,
    LOW
}