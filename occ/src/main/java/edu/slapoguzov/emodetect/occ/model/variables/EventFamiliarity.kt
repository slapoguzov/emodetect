package edu.slapoguzov.emodetect.occ.model.variables

/**
 *
 * AVERAGE between verb and subject
 *
 * eat sushi - COMMON
 * buy diamond ring -  UNCOMMON
 */
enum class EventFamiliarity : IntensityVariable {
    COMMON,
    UNCOMMON
}