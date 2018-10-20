package edu.slapoguzov.emodetect.relations.model.connl

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
}