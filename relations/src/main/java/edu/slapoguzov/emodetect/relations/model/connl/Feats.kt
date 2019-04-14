package edu.slapoguzov.emodetect.relations.model.connl

enum class Feats(val token: String) {
    PRESENT_TENSE("Tense=Pres"),
    PAST_TENSE("Tense=Past"),
    ANIMATE("Animacy=Anim"),
    INANIMATE("Animacy=Inan"),
    FIRST_PERSON("person=1"),
    DEGREE("Degree="),
    MODAL("Mood="),
    UNEXPECTED("Unexpected=true")
}