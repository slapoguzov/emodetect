package edu.slapoguzov.emodetect.occ.model.variables

/**
 * The value to the variable unexp is set ‘true’ if here is a linguistic token
 * to represent suddenness (e.g., abruptly, suddenly, etc.); otherwise ‘false’ is set.
 */
enum class Unexpectedness : EventBasedVariable {
    TRUE,
    FALSE
}