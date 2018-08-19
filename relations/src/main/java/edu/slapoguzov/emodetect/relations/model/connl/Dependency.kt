package edu.slapoguzov.emodetect.relations.model.connl

data class Dependency(
    val dependent: Token,
    val type: DependencyType
)