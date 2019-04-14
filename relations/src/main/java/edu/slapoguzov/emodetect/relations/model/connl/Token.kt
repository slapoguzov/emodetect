package edu.slapoguzov.emodetect.relations.model.connl

import edu.slapoguzov.emodetect.relations.model.connl.DependencyType.*

data class Token(
        val form: String,
        val lemma: String,
        val partOfSpeach: PartOfSpeach,
        val feats: List<Feats> = emptyList(),
        val position: Int,
        val dependencies: MutableList<Dependency>,
        val misc: String? = null
) {
    fun addDependencies(deps: List<Dependency>) {
        this.dependencies += deps
    }

    fun getDeepDependencies(): List<Dependency> {
        val nextLevel = dependencies.flatMap { it.dependent.dependencies.filter { it.type == CONJUNCT } }
        return dependencies + nextLevel + nextLevel.flatMap { it.dependent.getDeepDependencies() }
    }
}