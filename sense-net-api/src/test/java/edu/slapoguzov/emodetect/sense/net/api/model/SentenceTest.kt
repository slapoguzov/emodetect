package edu.slapoguzov.emodetect.sense.net.api.model

import org.junit.Test

class SentenceTest {

    @Test
    fun example1() {
        /* Я не видел Джона несколько часов; Я думал, что он может пропустить рейс,
           но внезапно я увидел его в самолете.’
         */
        val sentence = Sentence().also {
            it.collocation = Collocation().also {
                it.subject = я
                it.action = не_видел_несколько_часов
                it.`object` = джона
                it.relations = listOf(CollocationRelation().also {
                    it.collocation = я_думал_что_он_может_пропустить_рейс_но_внезапно_я_увидел_его_в_самолете
                    it.type = CollocationRelationType.AND
                })
            }
        }
    }

    val я_думал_что_он_может_пропустить_рейс_но_внезапно_я_увидел_его_в_самолете = Collocation().also {
        it.subject = я
        it.action = думал
        it.relations = listOf(CollocationRelation().also {
            it.collocation = он_может_пропустить_рейс
            it.type = CollocationRelationType.EXPLANATORY
        }, CollocationRelation().also {
            it.collocation = я_внезапно_увидел_его_в_самолете
            it.type = CollocationRelationType.BUT
        })
    }

    val он_может_пропустить_рейс = Collocation().also {
        it.subject = он
        it.action = может_пропустить
        it.`object` = рейс
    }

    val рейс = Object().also {
        it.objectType = ObjectType.ENTITY
        it.words = listOf(Word().also {
            it.text = "рейс"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.NOUN)
        })
        it.valence = 0.2
        it.numberPositiveSenses = 8
        it.numberNegativeSenses = 2
    }

    val может_пропустить = Action().also {
        it.words = listOf(Word().also {
            it.text = "пропустить"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.VERB, MorphoCharacteristic.PAST)
        }, Word().also {
            it.text = "может"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.VERB)
        })
        it.numberNegativeSenses = 8
        it.numberPositiveSenses = 2
        it.valence = -0.8
        it.relations = listOf()
    }

    val он = Subject().also {
        it.subjectType = SubjectType.PERSON
        it.words = listOf(Word().also {
            it.text = "он"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.PRONOUN)
        })
        it.valence = 0.0
        it.numberNegativeSenses = 5
        it.numberPositiveSenses = 5
    }

    val я_внезапно_увидел_его_в_самолете = Collocation().also {
        it.subject = я
        it.action = внезапно_увидел_в_самолете
        it.`object` = его
    }

    val внезапно_увидел_в_самолете = Action().also {
        it.words = listOf(Word().also {
            it.text = "увидел"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.VERB, MorphoCharacteristic.PRESENT)
        })
        it.valence = -0.2
        it.numberNegativeSenses = 8
        it.numberPositiveSenses = 2


        it.relations = listOf(внезапно, самолете)
    }

    val его = Object().also {
        it.objectType = ObjectType.PERSON
        it.words = listOf(Word().also {
            it.text = "его"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.PRONOUN)
        })
        it.valence = 0.0
        it.numberNegativeSenses = 5
        it.numberPositiveSenses = 5
    }

    val самолете = Object().also {
        it.objectType = ObjectType.ENTITY
        it.words = listOf(Word().also {
            it.text = "самолете"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.ADVERB)
        })
        it.numberNegativeSenses = 2
        it.numberPositiveSenses = 8
        it.valence = 0.8
    }

    val внезапно = Adverbial().also {
        it.words = listOf(Word().also {
            it.text = "внезапно"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.ADVERB)
        })
        it.valence = -0.2
        it.numberNegativeSenses = 8
        it.numberPositiveSenses = 2
    }

    val думал = Action().also {
        it.words = listOf(Word().also {
            it.text = "думал"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.VERB, MorphoCharacteristic.PAST)
        })
        it.valence = 0.5
        it.numberPositiveSenses = 10
        it.numberNegativeSenses = 5
    }

    val я = Subject().also {
        it.subjectType = SubjectType.SELF
        it.words = listOf(Word().also {
            it.text = "я"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.PRONOUN)
        })
        it.valence = 0.0
        it.numberNegativeSenses = 5
        it.numberPositiveSenses = 5
    }

    val не_видел_несколько_часов = Action().also {
        it.words = listOf(Word().also {
            it.text = "не видел"
            it.negation = true
            it.morphoCharacteristics = listOf(MorphoCharacteristic.VERB, MorphoCharacteristic.PAST)
        })
        it.valence = -0.2
        it.numberNegativeSenses = 8
        it.numberPositiveSenses = 2
        it.relations = listOf(
                Attribute().also {
                    it.words = listOf(Word().also {
                        it.text = "несколько"
                        it.negation = false
                        it.morphoCharacteristics = listOf(MorphoCharacteristic.NUMERAL)
                    })
                    it.valence = 0.0
                    it.numberNegativeSenses = 5
                    it.numberPositiveSenses = 5
                    it.relations = listOf(
                            Object().also {
                                it.words = listOf(Word().also {
                                    it.text = "часов"
                                    it.negation = false
                                    it.morphoCharacteristics = listOf(MorphoCharacteristic.NOUN)
                                })
                                it.objectType = ObjectType.ENTITY
                                it.valence = 0.0
                                it.numberNegativeSenses = 5
                                it.numberPositiveSenses = 5
                            }
                    )
                }
        )
    }
    val джона = Object().also {
        it.objectType = ObjectType.PERSON
        it.words = listOf(Word().also {
            it.text = "Джон"
            it.negation = false
            it.morphoCharacteristics = listOf(MorphoCharacteristic.NOUN)
        })
        it.valence = 0.0
        it.numberNegativeSenses = 1
        it.numberPositiveSenses = 1
    }

}