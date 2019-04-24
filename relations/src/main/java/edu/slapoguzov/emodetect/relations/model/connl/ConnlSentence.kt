package edu.slapoguzov.emodetect.relations.model.connl

import edu.slapoguzov.emodetect.core.conll.extension.Relation
import edu.slapoguzov.emodetect.core.conll.extension.Token

data class ConnlSentence(
        val tokens: List<Token>
) {
    val allRelations = tokens.flatMap { src ->
        src.dependencies.map {
            Relation(src, it.dependent, it.type)
        }
    }.distinct()
}