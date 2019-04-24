package edu.slapoguzov.emodetect.core.conll.extension

import edu.slapoguzov.emodetect.core.conll.DependencyRelation

data class Relation(
        val src: Token,
        val target: Token,
        val dependencyType: DependencyRelation
)