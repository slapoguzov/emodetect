package edu.slapoguzov.emodetect.relations.model.connl

data class Token(
        val form: String,
        val lemma: String,
        val partOfSpeach: PartOfSpeach,
        val feats: String?,
        val position: Int,
        val dependencies: MutableList<Dependency>
) {
    fun addDependencies(deps: List<Dependency>) {
        this.dependencies += deps
    }
}