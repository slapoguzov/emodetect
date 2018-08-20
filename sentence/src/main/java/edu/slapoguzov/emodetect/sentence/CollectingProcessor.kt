package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.relations.RelationsExtractor
import edu.slapoguzov.emodetect.relations.model.connl.Token
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.model.*
import edu.slapoguzov.emodetect.sentence.mystem.model.StemUnit
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import mu.KLogging

class CollectingProcessor(
        private val morphoProcessor: MorphoProcessor,
        private val relationExtractor: RelationsExtractor,
        private val relationProcessor: RelationProcessor,
        private val statisticsComponent: StatisticsComponent
) {

    fun process(text: String): Sentence {
        val morphoUnits = morphoProcessor.process(text)
        val connlSentence = relationExtractor.extract(text)
        val allWords = mutableListOf<Word>() // TODO: не факт, что хорошее решение
        connlSentence.allRelations.forEach {
            val relationChars = relationProcessor.process(RelationProcessorParameters(it.dependencyType))
            val src = buildWord(it.src, morphoUnits, relationChars.srcCharacteristics)
            val target = buildWord(it.target, morphoUnits, relationChars.targetCharacteristics)
            allWords.merge(src)
            allWords.merge(target)
        }

        val clauseValence = statisticsComponent.getPopularity(allWords.mapNotNull { it.lemma })
        val clause = Clause(allWords, emptyList(), clauseValence)

        val sentenceValence = statisticsComponent.getPopularity(allWords.mapNotNull { it.lemma })
        return Sentence(listOf(clause), sentenceValence)
    }

    private fun buildWord(token: Token, morphoUnits: Map<Int, StemUnit>, srcCharacteristics: List<Characteristic>): Word {
        val tokenPartOfSpeach = token.partOfSpeach.toPartOfSpeach()
        val partOfSpeech = if (tokenPartOfSpeach == PartOfSpeech.OTHER) {
            morphoUnits[token.position]?.partOfSpeach?.toPartOfSpeach()
        } else tokenPartOfSpeach
        val popularity = token.lemma?.let { statisticsComponent.getPopularity(it) } ?: 0.0
        val valence = token.lemma?.let { statisticsComponent.getValence(it) } ?: 0.0
        return Word(
                form = token.form,
                lemma = token.lemma,
                partOfSpeech = partOfSpeech ?: throw Exception(""),
                popularity = popularity,
                characteristics = srcCharacteristics,
                position = token.position,
                valence = valence
        )
    }

    private fun MutableList<Word>.merge(actualWord: Word) {
        val oldWorld = this.find { it.position == actualWord.position }
        if (oldWorld == null) {
            this += actualWord;
            return
        }
        val newWord = Word(
                form = oldWorld.form,
                lemma = oldWorld.lemma,
                partOfSpeech = oldWorld.partOfSpeech,
                popularity = oldWorld.popularity,
                position = oldWorld.position,
                characteristics = (oldWorld.characteristics + actualWord.characteristics).distinct(),
                valence = oldWorld.valence
        )
        this.remove(oldWorld)
        this.add(newWord)
    }

    private companion object : KLogging()
}