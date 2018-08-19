package edu.slapoguzov.emodetect.sentence.entity

import edu.slapoguzov.emodetect.relations.model.connl.DependencyType
import edu.stanford.nlp.trees.GrammaticalRelation

data class RelationProcessorParameters(
        val dependencyType: DependencyType
)