package edu.slapoguzov.emodetect.relations

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
        val lines = text.split(NEW_LINE)
                .filter { it.isNotEmpty() }
                .map { split(it) }
        val tokens = lines.associate { it.id to it.toToken()}
        val dependencies = lines.groupBy({ it.dep }, { tokens[it.id]!! })
        tokens.forEach { (_, tkn) ->
            val dependants = dependencies[tkn.position.toString()] ?: emptyList()
            val deps = dependants.map {
                val depType = lines[it.position - 1].depType ?: DependencyType.OTHER.text
                val dependencyType = DependencyType.of(depType)
                Dependency(it, dependencyType)
            }
            tkn.addDependencies(deps)
        }
        return ConnlSentence(tokens.values.toList())
    }

    private fun split(it: String): SourceLine {
        val tokens = it.split(DELIMITER).map { if (it == "_") null else it }
        return SourceLine(
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

    data class SourceLine(
            val id: String,
            val form: String,
            val lemma: String?,
            val cpostag: String?,
            val postag: String?,
            val feats: String?,
            val dep: String?,
            val depType: String?,
            val head: String?,
            val misc: String?
    ) {
        fun toToken(): Token {
            val pos = this.cpostag ?: PartOfSpeach.OTHER.text
            return Token(
                    form = this.form,
                    lemma = this.lemma,
                    partOfSpeach = PartOfSpeach.of(pos)!!,
                    feats = this.feats,
                    position = this.id.toInt(),
                    dependencies = mutableListOf()
            )
        }
    }

}