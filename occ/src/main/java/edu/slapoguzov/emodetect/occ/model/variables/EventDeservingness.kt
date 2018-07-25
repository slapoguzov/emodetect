package edu.slapoguzov.emodetect.occ.model.variables

/**
 * It indicates the degree to which ‘self’ desires the event
 * for ‘oneself’ as well as for ‘others.’
 *
 * In the model, the value for the intensity variable EventDeservingness
 * is set ‘high’ for an event having a higher positive valence (i.e., above +7.0)
 * or ‘low’ for higher valence in the negative scale (i.e., less than −7.0)
 */
enum class EventDeservingness : IntensityVariable {
    HIGH,
    LOW
}