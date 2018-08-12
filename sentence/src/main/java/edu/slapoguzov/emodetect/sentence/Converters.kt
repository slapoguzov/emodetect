package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.sentence.model.PartOfSpeech
import edu.slapoguzov.emodetect.sentence.mystem.model.Grammem

fun Grammem.toPartOfSpeach(): PartOfSpeech {
    return when (this) {
        Grammem.ADJECTIVE -> PartOfSpeech.ADJECTIVE
        Grammem.ADVERB -> PartOfSpeech.ADVERB
        Grammem.ADVERB_PRO -> PartOfSpeech.ADVERB
        Grammem.ADJECTIVE_NUM -> PartOfSpeech.ADJECTIVE
        Grammem.ADJECTIVE_PRO -> PartOfSpeech.ADJECTIVE
        Grammem.COMPOSITE -> throw IllegalStateException("Unsupported grammem")
        Grammem.CONJUNCTION -> PartOfSpeech.CONJUNCTION
        Grammem.INTERJECTION -> PartOfSpeech.INTERJECTION
        Grammem.NUMERAL -> PartOfSpeech.NUMERAL
        Grammem.PARTICLE -> PartOfSpeech.PARTICLE
        Grammem.PREPOSITION -> throw IllegalStateException("Unsupported grammem")
        Grammem.NOUN -> PartOfSpeech.NOUN
        Grammem.NOUN_PRO -> PartOfSpeech.NOUN
        Grammem.VERB -> PartOfSpeech.VERB
        else -> throw IllegalStateException("Unsupported grammem")

    }
}