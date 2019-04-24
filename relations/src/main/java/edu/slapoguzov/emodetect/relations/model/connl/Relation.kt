package edu.slapoguzov.emodetect.relations.model.connl

import edu.slapoguzov.emodetect.core.conll.extension.Token

data class Relation(
        val src: Token,
        val target: Token,
        val dependencyType: DependencyType
)