package edu.slapoguzov.emodetect.core.conll.extension

import edu.slapoguzov.emodetect.core.conll.DependencyRelation

data class Dependency(
        val dependent: Token,
        val type: DependencyRelation
)