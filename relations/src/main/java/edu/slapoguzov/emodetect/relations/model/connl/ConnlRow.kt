package edu.slapoguzov.emodetect.relations.model.connl

data class ConnlRow(
        val id: String,
        val form: String,
        var lemma: String?,
        val cpostag: String?,
        var postag: String?,
        val feats: String?,
        val dep: String?,
        val depType: String?,
        val head: String?,
        var misc: String?
) {
    fun toToken(): Token {
        val pos = this.cpostag ?: PartOfSpeach.OTHER.text
        return Token(
                form = this.form,
                lemma = this.lemma ?: this.form.toLemma(),
                partOfSpeach = PartOfSpeach.of(pos)!!,
                feats = this.feats,
                position = this.id.toInt(),
                dependencies = mutableListOf(),
                misc = misc
        )
    }

    private fun String.toLemma(): String {
        return this.replace(NON_ALPHABETIC, "").toLowerCase()
    }

    companion object {
        private val NON_ALPHABETIC = Regex("\\P{L}")
    }
}