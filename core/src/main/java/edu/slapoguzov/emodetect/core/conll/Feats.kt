package edu.slapoguzov.emodetect.core.conll

enum class Feats(override val token: String) : Feat {
    PRESENT_TENSE("Tense=Pres"),
    PAST_TENSE("Tense=Past"),
    ANIMATE("Animacy=Anim"),
    INANIMATE("Animacy=Inan"),
    FIRST_PERSON("person=1"),
    DEGREE("Degree="),
    MODAL("Mood="),
    UNEXPECTED("Unexpected=true")
}