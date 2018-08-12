package edu.slapoguzov.emodetect.sentence.mystem.model

enum class Grammem(val text: String) {
    ADJECTIVE("A"),
    ADVERB("ADV"),
    ADVERB_PRO("ADVPRO"),
    ADJECTIVE_NUM("ANUM"),
    ADJECTIVE_PRO("APRO"),
    COMPOSITE("COM"),
    CONJUNCTION("CONJ"),
    INTERJECTION("INTJ"),
    NUMERAL("NUM"),
    PARTICLE("PART"),
    PREPOSITION("PR"),
    NOUN("S"),
    NOUN_PRO("SPRO"),
    VERB("V"),

    PAST_TENSE("intr=praet"),
    PRESENT_TENSE("intr=praes"),
    NON_PAST_TENSE("intr=inpraes"),

    ANIMATE("anim"),
    INANIMATE("inan"),

    GEO("geo"), //географическое название
    PERSON("persn"), //имя собственное

    DISTORTED("dist"), //искаженная форма

    OBSC("obsc"), //обсценная лексика
    INFORM("inform"), //разговорная форма
    RARE("rare"), //редко встречающееся слово
    OBSOL("obsol"), //устаревшая форма
    FAMN("famn"); //фамилия

    companion object {

        val partsOfSpeach = listOf(ADJECTIVE, ADVERB, ADVERB_PRO, ADJECTIVE_NUM, ADJECTIVE_PRO, COMPOSITE, CONJUNCTION, INTERJECTION, NUMERAL, PARTICLE, PREPOSITION, NOUN, NOUN_PRO, VERB)

        fun fromText(text: String): Grammem? {
            return Grammem.values().find { Regex("${it.text}(=.*)?").matches(text) }
        }
    }
}