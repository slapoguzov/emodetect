package edu.slapoguzov.emodetect.core.conll

enum class UniversalPos(val text: String) {
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

    override fun toString(): String {
        return text
    }

    companion object {
        fun parse(value: String): UniversalPos? {
            return UniversalPos.values().find { it.text == value }
        }
    }
}
