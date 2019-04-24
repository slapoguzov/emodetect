package edu.slapoguzov.emodetect.sentence

import edu.slapoguzov.emodetect.core.conll.extension.ConnlSentence
import edu.slapoguzov.emodetect.relations.RelationsExtractor
import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach.*
import edu.slapoguzov.emodetect.core.conll.extension.Token
import edu.slapoguzov.emodetect.sentence.entity.RelationProcessorParameters
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Clause
import edu.slapoguzov.emodetect.sentence.model.Sentence
import edu.slapoguzov.emodetect.sentence.model.Word
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import mu.KLogging

class CollectingProcessor(
        private val relationExtractor: RelationsExtractor,
        private val relationProcessor: RelationProcessor,
        private val statisticsComponent: StatisticsComponent
) {

    fun process(text: String): Sentence {
        val relations = relationExtractor.extract(text)
        val connlSentence = ConnlSentence.of(relations)
        val allWords = mutableListOf<Word>() // TODO: не факт, что хорошее решение
        connlSentence.allRelations.forEach {
            val relationChars = relationProcessor.process(RelationProcessorParameters(it))
            val srcOtherMorphoChars = it.src.feats.mapNotNull { it.toCharacteristic() }
            val targetOtherMorphoChars = it.target.feats.mapNotNull { it.toCharacteristic() }
            val srcAllChars = relationChars.srcCharacteristics + srcOtherMorphoChars
            val targetAllChars = relationChars.targetCharacteristics + targetOtherMorphoChars
            val src = buildWord(it.src, srcAllChars)
            val target = buildWord(it.target, targetAllChars)
            allWords.merge(src)
            allWords.merge(target)
        }

        val lemmas = allWords.mapNotNull { it.lemma }
        val clauseValence = statisticsComponent.getValence(lemmas)
        val clause = Clause(allWords, emptyList(), clauseValence)

        val sentenceValence = statisticsComponent.getValence(lemmas)
        return Sentence(listOf(clause), sentenceValence)
    }

    private fun buildWord(token: Token, srcCharacteristics: List<Characteristic>): Word {
        val popularity = token.lemma.let { statisticsComponent.getPopularity(it) }
        val depsValence = token.getDeepDependencies()
                .filter { it.dependent.partOfSpeach == ADJECTIVE }
                .map { statisticsComponent.getValence(it.dependent.lemma) }
                .average()
        val tokenValence = token.lemma.let { statisticsComponent.getValence(it) }
        val avgValence = if(depsValence.isNaN()) listOf(tokenValence).average() else listOf(depsValence, tokenValence).average()
        val valence = if(avgValence.isNaN()) 0.0 else avgValence
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