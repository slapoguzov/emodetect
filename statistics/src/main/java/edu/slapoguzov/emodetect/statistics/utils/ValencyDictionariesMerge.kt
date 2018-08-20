package edu.slapoguzov.emodetect.statistics.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.opencsv.bean.CsvToBeanBuilder
import edu.slapoguzov.emodetect.core.getPathToResource
import edu.slapoguzov.emodetect.statistics.entity.LinisCrowdRow
import edu.slapoguzov.emodetect.statistics.entity.RussentilexRow
import edu.slapoguzov.emodetect.statistics.entity.Valence
import edu.slapoguzov.emodetect.statistics.entity.ValencedWord
import java.io.File
import java.io.FileReader

object ValencyDictionariesMerge {

    private val mapper = ObjectMapper().writerWithDefaultPrettyPrinter()

    fun merge() {
        val linisCrowdRows = readLinisCrowd("valency/linis-crowd-2016.csv")
        val russentilexRows = readRusentilex("valency/rusentilex_2017.txt")

        val valencedWords = mutableMapOf<String, ValencedWord>()

        linisCrowdRows.forEach { valencedWords.merge(it.word, it.valency) }
        russentilexRows.forEach { valencedWords.merge(it.word, it.valency) }
        mapper.writeValue(File("valenceDictionary.json"), valencedWords.values)
        println(linisCrowdRows)
    }

    private fun MutableMap<String, ValencedWord>.merge(token: String, valence: Valence) {
        val oldWord = this[token]
        val newWord = buildValencedWord(token, valence, oldWord)
        if (oldWord != null) this.remove(token)
        this += newWord.token to newWord
    }

    private fun readRusentilex(path: String): List<RussentilexRow> {
        val pathToDictinary = this.javaClass.getPathToResource(path)
        val russentilexRows = CsvToBeanBuilder<RussentilexRow>(FileReader(pathToDictinary))
                .withType(RussentilexRow::class.java)
                .withIgnoreQuotations(true)
                .build()
                .parse()
        return russentilexRows
    }

    private fun readLinisCrowd(path: String): List<LinisCrowdRow> {
        val pathToDictinary = this.javaClass.getPathToResource(path)
        val linisCrowdRows = CsvToBeanBuilder<LinisCrowdRow>(FileReader(pathToDictinary))
                .withSeparator(';')
                .withType(LinisCrowdRow::class.java)
                .build()
                .parse()
        return linisCrowdRows
    }

    private fun buildValencedWord(token: String, valence: Valence, oldWord: ValencedWord? = null): ValencedWord {
        val oldValence = oldWord?.valence ?: 0.0
        val oldNumberPositiveSenses = oldWord?.numberPositiveSenses ?: 0
        val oldNumberNegativeSenses = oldWord?.numberNegativeSenses ?: 0
        val oldNumberNeutralSenses = oldWord?.numberNeutralSenses ?: 0

        var newNumberPositiveSenses = oldNumberPositiveSenses
        var newNumberNegativeSenses = oldNumberNegativeSenses
        var newNumberNeutralSenses = oldNumberNegativeSenses

        when (valence) {
            Valence.NEGATIVE -> newNumberNegativeSenses += 1
            Valence.POSITIVE -> newNumberPositiveSenses += 1
            Valence.NEUTRAL -> newNumberNeutralSenses += 1
        }

        val total = (newNumberPositiveSenses + newNumberNegativeSenses + newNumberNeutralSenses).toDouble()
        val newValence = (newNumberPositiveSenses - newNumberNegativeSenses) / total
        return ValencedWord(
                token = token,
                valence = newValence,
                numberNegativeSenses = newNumberNegativeSenses,
                numberNeutralSenses = newNumberNeutralSenses,
                numberPositiveSenses = newNumberPositiveSenses
        )
    }
}