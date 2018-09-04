package edu.slapoguzov.emodetect.sentence.entity

import edu.slapoguzov.emodetect.relations.model.connl.DependencyType
import edu.slapoguzov.emodetect.relations.model.connl.PartOfSpeach
import edu.slapoguzov.emodetect.relations.model.connl.Relation
import edu.stanford.nlp.trees.GrammaticalRelation

data class RelationProcessorParameters(
        val srcPos: PartOfSpeach,
        val targetPos: PartOfSpeach,
        val dependencyType: DependencyType
) {
    constructor(relation: Relation) : this(
            relation.src.partOfSpeach,
            relation.target.partOfSpeach,
            relation.dependencyType
    )
}