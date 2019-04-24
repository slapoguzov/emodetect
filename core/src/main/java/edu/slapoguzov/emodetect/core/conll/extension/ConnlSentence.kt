package edu.slapoguzov.emodetect.core.conll.extension

import edu.slapoguzov.emodetect.core.conll.Conll
import edu.slapoguzov.emodetect.core.conll.DependencyRelation

data class ConnlSentence(
        val tokens: List<Token>
) {
    companion object {
        fun of(conll: Conll): ConnlSentence {
            val tokens = conll.rows.associate { it.id to Token.of(it) }
            val dependencies = conll.rows.groupBy({ it.deps }, { tokens[it.id]!! })
            tokens.forEach { (_, tkn) ->
                val dependants = dependencies[tkn.position] ?: emptyList()
                val deps = dependants.map {
                    val depType = conll.rows[it.position - 1].depRel ?: DependencyRelation.OTHER
                    Dependency(it, depType)
                }
                tkn.addDependencies(deps)
            }
            return ConnlSentence(tokens.values.toList())
        }
    }


    val allRelations = tokens.flatMap { src ->
        src.dependencies.map {
            Relation(src, it.dependent, it.type)
        }
    }.distinct()
}