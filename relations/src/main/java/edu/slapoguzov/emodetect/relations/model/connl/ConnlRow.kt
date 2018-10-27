package edu.slapoguzov.emodetect.relations.model.connl

data class ConnlRow(
        val id: String,
        val form: String,
        var lemma: String?,
        val cpostag: String?,
        var postag: String?,
        var feats: String?,
        val dep: String?,
        val depType: String?,
        val head: String?,
        val misc: String?
) {
    fun toToken(): Token {
        val pos = this.cpostag ?: PartOfSpeach.OTHER.text
        val parsedFeats = this.feats
                ?.split("|")
                .orEmpty()
                .mapNotNull { str -> Feats.values().find { str.contains(it.token) } }
        return Token(
                form = this.form,
                lemma = this.lemma ?: this.form.toLemma(),
                partOfSpeach = PartOfSpeach.of(pos)!!,
                feats = parsedFeats,
                position = this.id.toInt(),
                dependencies = mutableListOf(),
                misc = misc
        )
    }

    private fun String.toLemma(): String {
        return this.replace(NON_ALPHABETIC, "").toLowerCase()
    }

    override fun toString(): String {
        return listOf(
                id,
                form,
                lemma,
                cpostag,
                postag?.toConnlUFormat(),
                feats?.toConnlUFormat(),
                dep,
                depType,
                head,
                misc
        ).joinToString("\t") { if (it == null) "_" else it }
    }

    private fun String.toConnlUFormat(): String {
        return this
                .replace("+", "")
                .replace("fPOS=", "POS=")
                .replace(Regex("PUNCT[^ ^\t]?"), "PUNCT")
                .split("|")
                .sortedBy { it.toLowerCase() }
                .joinToString("|")
    }

    companion object {
        private val NON_ALPHABETIC = Regex("\\P{L}")
    }


}