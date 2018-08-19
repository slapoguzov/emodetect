package edu.slapoguzov.emodetect.relations.model.connl

data class ConnlSentence(
        val tokens: List<Token>
) {
    val allRelations = tokens.flatMap { src ->
        src.dependencies.map {
            Relation(src, it.dependent, it.type)
        }
    }.distinct()
}