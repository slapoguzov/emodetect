package edu.slapoguzov.emodetect.statistics

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import edu.slapoguzov.emodetect.core.readResource
import edu.slapoguzov.emodetect.statistics.entity.PopularityDictionary
import java.io.File

object PopularityDictionaryFactory {
    private val mapper = ObjectMapper().registerKotlinModule()
    private val file = this.javaClass.readResource("popularity/popularityDictionary.json")
    val instance = mapper.readValue(file, PopularityDictionary::class.java)
}