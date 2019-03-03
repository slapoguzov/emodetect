package edu.slapoguzov.emodetect.morpho

import edu.slapoguzov.emodetect.core.readResourceByLines
import edu.slapoguzov.emodetect.morpho.model.Grammem
import java.io.File

class AdditionalMorphoDictionary {
    private val lines = this.javaClass.readResourceByLines("additional_morpho.dict")
    private val words = lines.associate { val columns = it.split(" ")
        columns[0] to columns.drop (1).map { Grammem.valueOf(it) }
    }

    fun getGrammes(word: String): List<Grammem> {
        return words[word] ?: emptyList()
    }
}