package edu.slapoguzov.emodetect.sentence.entity

import edu.slapoguzov.emodetect.relations.model.connl.DependencyType
import edu.slapoguzov.emodetect.relations.model.connl.Relation
import edu.slapoguzov.emodetect.relations.model.connl.Token

data class RelationProcessorParameters(
        val src: Token,
        val target: Token,
        val dependencyType: DependencyType
) {
    constructor(relation: Relation) : this(
            relation.src,
            relation.target,
            relation.dependencyType
    )
}