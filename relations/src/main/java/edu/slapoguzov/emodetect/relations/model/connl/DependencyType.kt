package edu.slapoguzov.emodetect.relations.model.connl

enum class DependencyType(val text: String) {
    ADJECTIVAL_CLAUSE("acl"),
    ADVERBIAL_CLAUSE_MODIFIER("advcl"),
    ADVERBIAL_MODIFIER("advmod"),
    ADJECTIVAL_MODIFIER("amod"),
    APPOSITIONAL_MODIFIER("appos"),
    AUXILIARY("aux"),
    CASE_MARKING("case"),
    COORDINATING_CONJUNCTION("cc"),
    CLAUSAL_COMPLEMENT("ccomp"),
    CLASSIFIER("clf"),
    COMPOUND("compound"),
    CONJUNCT("conj"),
    COPULA("cop"),
    CLAUSAL_SUBJECT("csubj"),
    UNSPECIFIED_DEPENDENCY("dep"),
    DETERMINER("det"),
    DISCOURSE_ELEMENT("discourse"),
    DISLOCATED_ELEMENTS("dislocated"),
    EXPLETIVE("expl"),
    FIXED_MULTIWORD("fixed"),
    FLAT_MULTIWORD("flat"),
    GOES_WITH("goeswith"),
    INDIRECT_OBJECT("iobj"),
    LIST("list"),
    MARKER("mark"),
    NOMINAL_MODIFIER("nmod"),
    NOMINAL_SUBJECT("nsubj"),
    NUMERIC_MODIFIER("nummod"),
    OBJECT("obj"),
    OBLIQUE_NOMINAL("obl"),
    ORPHAN("orphan"),
    PARATAXIS("parataxis"),
    PUNCTUATION("punct"),
    OVERRIDDEN_DISFLUENCY("reparandum"),
    ROOT("root"),
    VOCATIVE("vocative"),
    OPEN_CLAUSAL_COMPLEMENT("xcomp"),
    OTHER("X");

    companion object {
        fun of(value: String): DependencyType {
            return DependencyType.values().find { it.text == value } ?: OTHER
        }
    }
}