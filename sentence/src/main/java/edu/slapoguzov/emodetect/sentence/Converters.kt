package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.core.conll.Feats
import edu.slapoguzov.emodetect.core.conll.UniversalPos
import edu.slapoguzov.emodetect.morpho.model.Grammem
import edu.slapoguzov.emodetect.sentence.model.PartOfSpeech
import edu.slapoguzov.emodetect.morpho.mystem.model.StemGrammem
import edu.slapoguzov.emodetect.sentence.model.Characteristic

fun UniversalPos.toPartOfSpeach(): PartOfSpeech {
    return when (this) {
        UniversalPos.ADJECTIVE -> PartOfSpeech.ADJECTIVE
        UniversalPos.ADPOSITION -> PartOfSpeech.OTHER
        UniversalPos.ADVERB -> PartOfSpeech.ADVERB
        UniversalPos.AUXILIARY -> PartOfSpeech.OTHER
        UniversalPos.CONJUNCTION -> PartOfSpeech.CONJUNCTION
        UniversalPos.COORDINATING_CONJUNCTION -> PartOfSpeech.CONJUNCTION
        UniversalPos.DETERMINER -> PartOfSpeech.PRONOUN
        UniversalPos.INTERJECTION -> PartOfSpeech.INTERJECTION
        UniversalPos.NOUN -> PartOfSpeech.NOUN
        UniversalPos.NUMERAL -> PartOfSpeech.NUMERAL
        UniversalPos.PARTICLE -> PartOfSpeech.PARTICLE
        UniversalPos.PRONOUN -> PartOfSpeech.PRONOUN
        UniversalPos.PROPER_NOUN -> PartOfSpeech.PRONOUN
        UniversalPos.PUNCTUATION -> PartOfSpeech.PUNCTUATION
        UniversalPos.SUBORDINATING_CONJUNCTION -> PartOfSpeech.CONJUNCTION
        UniversalPos.SYMBOL -> PartOfSpeech.OTHER
        UniversalPos.VERB -> PartOfSpeech.VERB
        UniversalPos.OTHER -> PartOfSpeech.OTHER
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

fun Feats.toCharacteristic(): Characteristic? {
    return when (this) {
        Feats.PAST_TENSE -> Characteristic.PAST_TENSE
        Feats.MODAL -> Characteristic.IS_MODAL
        Feats.UNEXPECTED -> Characteristic.IS_SUDDENLY
        Feats.ANIMATE -> Characteristic.ANIMATE
        Feats.INANIMATE -> Characteristic.INANIMATE
        Feats.FIRST_PERSON -> Characteristic.FIRST_PERSON
        else -> null
    }
}