package edu.slapoguzov.emodetect.relations.model.connl

import edu.slapoguzov.emodetect.core.conll.extension.Token

data class Dependency(
        val dependent: Token,
        val type: DependencyType
)