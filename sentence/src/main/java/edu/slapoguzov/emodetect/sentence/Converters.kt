package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach as ConnlPartOfSpeach
import edu.slapoguzov.emodetect.sentence.model.PartOfSpeech
import edu.slapoguzov.emodetect.sentence.mystem.model.Grammem

fun ConnlPartOfSpeach.toPartOfSpeach(): PartOfSpeech {
    return when (this) {
        ConnlPartOfSpeach.ADJECTIVE -> PartOfSpeech.ADJECTIVE
        ConnlPartOfSpeach.ADPOSITION -> PartOfSpeech.OTHER
        ConnlPartOfSpeach.ADVERB -> PartOfSpeech.ADVERB
        ConnlPartOfSpeach.AUXILIARY -> PartOfSpeech.OTHER
        ConnlPartOfSpeach.CONJUNCTION -> PartOfSpeech.CONJUNCTION
        ConnlPartOfSpeach.COORDINATING_CONJUNCTION -> PartOfSpeech.CONJUNCTION
        ConnlPartOfSpeach.DETERMINER -> PartOfSpeech.PRONOUN
        ConnlPartOfSpeach.INTERJECTION -> PartOfSpeech.INTERJECTION
        ConnlPartOfSpeach.NOUN -> PartOfSpeech.NOUN
        ConnlPartOfSpeach.NUMERAL -> PartOfSpeech.NUMERAL
        ConnlPartOfSpeach.PARTICLE -> PartOfSpeech.PARTICLE
        ConnlPartOfSpeach.PRONOUN -> PartOfSpeech.PRONOUN
        ConnlPartOfSpeach.PROPER_NOUN -> PartOfSpeech.PRONOUN
        ConnlPartOfSpeach.PUNCTUATION -> PartOfSpeech.PUNCTUATION
        ConnlPartOfSpeach.SUBORDINATING_CONJUNCTION -> PartOfSpeech.CONJUNCTION
        ConnlPartOfSpeach.SYMBOL -> PartOfSpeech.OTHER
        ConnlPartOfSpeach.VERB -> PartOfSpeech.VERB
        ConnlPartOfSpeach.OTHER -> PartOfSpeech.OTHER
        else -> PartOfSpeech.OTHER
    }
}

fun Grammem.toPartOfSpeach(): PartOfSpeech {
    return when (this) {
        Grammem.ADJECTIVE -> PartOfSpeech.ADJECTIVE
        Grammem.ADVERB -> PartOfSpeech.ADVERB
        Grammem.ADVERB_PRO -> PartOfSpeech.ADVERB
        Grammem.ADJECTIVE_NUM -> PartOfSpeech.ADJECTIVE
        Grammem.ADJECTIVE_PRO -> PartOfSpeech.ADJECTIVE
        Grammem.COMPOSITE -> PartOfSpeech.OTHER
        Grammem.CONJUNCTION -> PartOfSpeech.CONJUNCTION
        Grammem.INTERJECTION -> PartOfSpeech.INTERJECTION
        Grammem.NUMERAL -> PartOfSpeech.NUMERAL
        Grammem.PARTICLE -> PartOfSpeech.PARTICLE
        Grammem.PREPOSITION -> PartOfSpeech.OTHER
        Grammem.NOUN -> PartOfSpeech.NOUN
        Grammem.NOUN_PRO -> PartOfSpeech.NOUN
        Grammem.VERB -> PartOfSpeech.VERB
        else -> PartOfSpeech.OTHER

    }
}