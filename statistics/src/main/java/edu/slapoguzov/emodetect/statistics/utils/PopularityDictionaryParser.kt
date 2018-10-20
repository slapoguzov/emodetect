package edu.slapoguzov.emodetect.statistics.utils

import com.fasterxml.jackson.databind.ObjectMapper
import edu.slapoguzov.emodetect.core.getPathToResource
import edu.slapoguzov.emodetect.statistics.entity.PopularityDictionary
import edu.slapoguzov.emodetect.statistics.entity.WordPopularity
import mu.KLogging
import java.io.File

object PopularityDictionaryParser : KLogging() {
    private val wordPopularityExtractor = WordPopularityExtractor()
    private val mapper = ObjectMapper().writerWithDefaultPrettyPrinter()

    fun parse() {
        val path = this.javaClass.getPathToResource("texts/linis-crowd-short.csv")
        val lines = File(path).readLines()
        val sentences = lines
                .flatMap { it.split(Regex("[ ]*\\.[ ]*")) }
                .filter { it.length > 20 }
                .drop(10)
        val words = mutableMapOf<String, WordPopularity>()
        sentences.forEachIndexed { i, it ->
            logger.info { "parse text $i" }
            words.merge(wordPopularityExtractor.extractWordPopularities(it))
        }
        val usage = words.values.sumBy { it.numberOfUse }
        val dictionary = PopularityDictionary(words, usage.toLong())
        mapper.writeValue(File("popularityDictionary.json"), dictionary)
    }
}