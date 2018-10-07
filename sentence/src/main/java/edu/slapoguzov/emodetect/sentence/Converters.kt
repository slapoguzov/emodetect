package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.morpho.model.Grammem
import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach as ConnlPartOfSpeach
import edu.slapoguzov.emodetect.sentence.model.PartOfSpeech
import edu.slapoguzov.emodetect.morpho.mystem.model.StemGrammem
import edu.slapoguzov.emodetect.sentence.model.Characteristic

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

fun StemGrammem.toPartOfSpeach(): PartOfSpeech {
    return when (this) {
        StemGrammem.ADJECTIVE -> PartOfSpeech.ADJECTIVE
        StemGrammem.ADVERB -> PartOfSpeech.ADVERB
        StemGrammem.ADVERB_PRO -> PartOfSpeech.ADVERB
        StemGrammem.ADJECTIVE_NUM -> PartOfSpeech.ADJECTIVE
        StemGrammem.ADJECTIVE_PRO -> PartOfSpeech.ADJECTIVE
        StemGrammem.COMPOSITE -> PartOfSpeech.OTHER
        StemGrammem.CONJUNCTION -> PartOfSpeech.CONJUNCTION
        StemGrammem.INTERJECTION -> PartOfSpeech.INTERJECTION
        StemGrammem.NUMERAL -> PartOfSpeech.NUMERAL
        StemGrammem.PARTICLE -> PartOfSpeech.PARTICLE
        StemGrammem.PREPOSITION -> PartOfSpeech.OTHER
        StemGrammem.NOUN -> PartOfSpeech.NOUN
        StemGrammem.NOUN_PRO -> PartOfSpeech.NOUN
        StemGrammem.VERB -> PartOfSpeech.VERB
        else -> PartOfSpeech.OTHER

    }
}

fun Grammem.toCharacteristic(): Characteristic? {
    return when (this) {
        Grammem.PAST_TENSE -> Characteristic.PAST_TENSE
        Grammem.MODAL -> Characteristic.IS_MODAL
        Grammem.UNEXPECTED -> Characteristic.IS_SUDDENLY
        else -> null
    }
}