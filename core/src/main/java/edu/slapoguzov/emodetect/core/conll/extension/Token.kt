package edu.slapoguzov.emodetect.core.conll.extension

import edu.slapoguzov.emodetect.core.conll.ConllRow
import edu.slapoguzov.emodetect.core.conll.DependencyRelation.CONJUNCT
import edu.slapoguzov.emodetect.core.conll.Feats
import edu.slapoguzov.emodetect.core.conll.UniversalPos
import edu.slapoguzov.emodetect.core.conll.misc.Misc

data class Token(
        val form: String,
        val lemma: String,
        val partOfSpeach: UniversalPos,
        val feats: Set<Feats> = emptySet(),
        val position: Int,
        val dependencies: MutableList<Dependency>,
        val misc: Set<Misc> = emptySet()
) {

    companion object {
        private val NON_ALPHABETIC = Regex("\\P{L}")

        fun of(conllRow: ConllRow): Token {
            val pos = conllRow.upos ?: UniversalPos.OTHER
            return Token(
                    form = conllRow.form,
                    lemma = conllRow.lemma ?: conllRow.form.toLemma(),
                    partOfSpeach = pos,
                    feats = conllRow.feats.orEmpty().filterIsInstance(Feats::class.java).toSet(),
                    position = conllRow.id,
                    dependencies = mutableListOf(),
                    misc = conllRow.misc.orEmpty().toSet()
            )
        }

        private fun String.toLemma(): String {
            return this.replace(NON_ALPHABETIC, "").toLowerCase()
        }
    }

    fun addDependencies(deps: List<Dependency>) {
        this.dependencies += deps
    }

    fun getDeepDependencies(): List<Dependency> {
        val nextLevel = dependencies.flatMap { it.dependent.dependencies.filter { it.type == CONJUNCT } }
        return dependencies + nextLevel + nextLevel.flatMap { it.dependent.getDeepDependencies() }
    }
}