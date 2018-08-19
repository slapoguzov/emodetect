package edu.slapoguzov.emodetect.relations.model.connl

enum class PartOfSpeach(val text: String) {
    ADJECTIVE("ADJ"),
    ADPOSITION("ADP"),
    ADVERB("ADV"),
    AUXILIARY("AUX"),
    CONJUNCTION("CONJ"),
    COORDINATING_CONJUNCTION("CCONJ"),
    DETERMINER("DET"),
    INTERJECTION("INTJ"),
    NOUN("NOUN"),
    NUMERAL("NUM"),
    PARTICLE("PART"),
    PRONOUN("PRON"),
    PROPER_NOUN("PROPN"),
    PUNCTUATION("PUNCT"),
    SUBORDINATING_CONJUNCTION("SCONJ"),
    SYMBOL("SYM"),
    VERB("VERB"),
    OTHER("X");

    companion object {
        fun of(value: String): PartOfSpeach? {
            return PartOfSpeach.values().find { it.text == value }
        }
    }
}