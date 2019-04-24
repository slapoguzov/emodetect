package edu.slapoguzov.emodetect.sentence.entity

import edu.slapoguzov.emodetect.core.conll.DependencyRelation
import edu.slapoguzov.emodetect.relations.model.connl.DependencyType
import edu.slapoguzov.emodetect.core.conll.extension.Relation
import edu.slapoguzov.emodetect.core.conll.extension.Token

data class RelationProcessorParameters(
        val src: Token,
        val target: Token,
        val dependencyType: DependencyRelation
) {
    constructor(relation: Relation) : this(
            relation.src,
            relation.target,
            relation.dependencyType
    )
}