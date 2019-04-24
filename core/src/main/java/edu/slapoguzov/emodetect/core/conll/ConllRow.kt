package edu.slapoguzov.emodetect.core.conll

import edu.slapoguzov.emodetect.core.conll.misc.Misc

data class ConllRow(
        /**
         * Word index, integer starting at 1 for each new sentence; may be a range for multiword tokens;
         * may be a decimal number for empty nodes (decimal numbers can be lower than 1 but must be greater than 0)
         */
        val id: Int,

        /**
         * Word form or punctuation symbol
         */
        val form: String,

        /**
         * Lemma or stem of word form
         */
        var lemma: String?,

        /**
         * Universal part-of-speech tag
         */
        val upos: UniversalPos?,

        /**
         * Language-specific part-of-speech tag; underscore if not available.
         */
        val xpos: String?,

        /**
         * List of morphological features from the universal feature inventory or from a defined
         * language-specific extension; underscore if not available.
         */
        var feats: MutableSet<Feat>?,

        /**
         * Head of the current word, which is either a value of id or zero (0).
         */
        val head: String?,

        /**
         *  Universal dependency relation to the HEAD (root iff HEAD = 0) or a defined language-specific subtype of one
         */
        val depRel: DependencyRelation?,

        /**
         * Enhanced dependency graph in the form of a list of head-deprel pairs.
         */
        val deps: Int?,

        /**
         * Any other annotation.
         */
        val misc: MutableSet<Misc>?
) {

    override fun toString(): String {
        return listOf(
                id,
                form,
                lemma,
                upos,
                xpos,
                feats?.joinToString(FEAT_DELIMITER),
                head,
                depRel,
                deps,
                misc?.joinToString(MISC_DELIMITER)
        ).map { it?.toString() }
                .joinToString(DELIMITER) { it ?: EMPTY_FIELD }
    }

    companion object {
        const val EMPTY_FIELD = "_"
        const val DELIMITER = "\t"
        const val MISC_DELIMITER = "|"
        const val FEAT_DELIMITER = "|"
    }
}
