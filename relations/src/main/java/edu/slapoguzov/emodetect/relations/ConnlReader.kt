package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.core.conll.extension.ConnlSentence
import edu.slapoguzov.emodetect.relations.model.connl.*

class ConnlReader {

    companion object {
        private val ID = 0
        private val FORM = 1
        private val LEMMA = 2
        private val CPOSTAG = 3
        private val POSTAG = 4
        private val FEATS = 5
        private val DEP = 6
        private val DEPTYPE = 7
        private val HEAD = 8
        private val MISC = 9

        private val NEW_LINE = Regex("\n")
        private val DELIMITER = Regex("\t+|[ ]+")
    }

    fun read(text: String): ConnlSentence {
        val lines = readSourceLines(text)
        return convertToConnlSentence(lines)
    }

    fun read(rows: List<ConnlRow>): ConnlSentence {
        return convertToConnlSentence(rows)
    }


    fun readSourceLines(text: String): List<ConnlRow> {
        val lines = text.split(NEW_LINE)
                .filter { it.isNotEmpty() }
                .map { split(it) }
        return lines
    }

    private fun convertToConnlSentence(rows: List<ConnlRow>): ConnlSentence {
        val tokens = rows.associate { it.id to it.toToken() }
        val dependencies = rows.groupBy({ it.dep }, { tokens[it.id]!! })
        tokens.forEach { (_, tkn) ->
            val dependants = dependencies[tkn.position.toString()] ?: emptyList()
            val deps = dependants.map {
                val depType = rows[it.position - 1].depType ?: DependencyType.OTHER.text
                val dependencyType = DependencyType.of(depType)
                edu.slapoguzov.emodetect.core.conll.extension.Dependency(it, dependencyType)
            }
            tkn.addDependencies(deps)
        }
        return ConnlSentence(tokens.values.toList())
    }

    private fun split(it: String): ConnlRow {
        val tokens = it.split(DELIMITER).map { if (it == "_") null else it }
        return ConnlRow(
                id = tokens[ID]!!,
                form = tokens[FORM]!!,
                lemma = tokens[LEMMA],
                cpostag = tokens[CPOSTAG],
                postag = tokens[POSTAG],
                feats = tokens[FEATS],
                dep = tokens[DEP],
                depType = tokens[DEPTYPE],
                head = tokens[HEAD],
                misc = tokens[MISC]
        )
    }
}