package edu.slapoguzov.emodetect.statistics

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import edu.slapoguzov.emodetect.core.getPathToResource
import edu.slapoguzov.emodetect.statistics.entity.PopularityDictionary
import java.io.File

object PopularityDictionaryFactory {
    private val mapper = ObjectMapper().registerKotlinModule()
    private val path = this.javaClass.getPathToResource("popularity/popularityDictionary.json")
    val instance = mapper.readValue(File(path), PopularityDictionary::class.java)
}