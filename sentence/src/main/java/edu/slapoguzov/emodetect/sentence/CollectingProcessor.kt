package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.morpho.model.Grammem
import edu.slapoguzov.emodetect.relations.RelationsExtractor
import edu.slapoguzov.emodetect.relations.model.connl.Token
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.model.*
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import mu.KLogging

class CollectingProcessor(
        private val relationExtractor: RelationsExtractor,
        private val relationProcessor: RelationProcessor,
        private val statisticsComponent: StatisticsComponent
) {

    fun process(text: String): Sentence {
        val connlSentence = relationExtractor.extract(text)
        val allWords = mutableListOf<Word>() // TODO: не факт, что хорошее решение
        connlSentence.allRelations.forEach {
            val relationChars = relationProcessor.process(RelationProcessorParameters(it))
            val srcOtherMorphoChars = it.src.misc
                    ?.split(",")
                    ?.filter { it.isNotEmpty() }
                    ?.mapNotNull { Grammem.valueOf(it).toCharacteristic() }
                    .orEmpty()
            val targetOtherMorphoChars = it.target.misc
                    ?.split(",")
                    ?.filter { it.isNotEmpty() }
                    ?.mapNotNull { Grammem.valueOf(it).toCharacteristic() }
                    .orEmpty()
            val srcAllChars = relationChars.srcCharacteristics + srcOtherMorphoChars
            val targetAllChars = relationChars.targetCharacteristics + targetOtherMorphoChars
            val src = buildWord(it.src, srcAllChars)
            val target = buildWord(it.target, targetAllChars)
            allWords.merge(src)
            allWords.merge(target)
        }

        val clauseValence = statisticsComponent.getValence(allWords.mapNotNull { it.lemma })
        val clause = Clause(allWords, emptyList(), clauseValence)

        val sentenceValence = statisticsComponent.getValence(allWords.mapNotNull { it.lemma })
        return Sentence(listOf(clause), sentenceValence)
    }

    private fun buildWord(token: Token, srcCharacteristics: List<Characteristic>): Word {
        val popularity = token.lemma.let { statisticsComponent.getPopularity(it) }
        val valence = token.lemma.let { statisticsComponent.getValence(it) }
        return Word(
                form = token.form,
                lemma = token.lemma,
                partOfSpeech = token.partOfSpeach.toPartOfSpeach(),
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