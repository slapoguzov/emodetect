package edu.slapoguzov.emodetect.core.conll.misc

import edu.slapoguzov.emonet.emodetect.api.model.conll.misc.EmotionMisc

class SimpleMisc(val key: String, val value: String) : Misc {
    override fun toString(): String {
        return key + EmotionMisc.KEY + value
    }

    companion object {
        const val KEY = "="
    }
}
