package edu.slapoguzov.emodetect.backend.api

import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType

data class EmotionDetectResponse(
        val emotions: Set<EmotionType>
)