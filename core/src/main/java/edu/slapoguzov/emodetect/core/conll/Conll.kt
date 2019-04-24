package edu.slapoguzov.emodetect.core.conll

data class Conll(
    val rows: List<ConllRow>
) {

    override fun toString(): String {
        return rows.joinToString("\n") { it.toString() }
    }
}
