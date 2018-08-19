package edu.slapoguzov.emodetect.relations.model.connl

data class Relation(
        val src: Token,
        val target: Token,
        val dependencyType: DependencyType
)