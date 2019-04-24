package edu.slapoguzov.emodetect.core.conll

import edu.slapoguzov.emodetect.core.conll.ConllRow.Companion.DELIMITER
import edu.slapoguzov.emodetect.core.conll.ConllRow.Companion.EMPTY_FIELD
import edu.slapoguzov.emodetect.core.conll.ConllRow.Companion.FEAT_DELIMITER
import edu.slapoguzov.emodetect.core.conll.ConllRow.Companion.MISC_DELIMITER
import edu.slapoguzov.emodetect.core.conll.misc.Misc
import edu.slapoguzov.emodetect.core.conll.misc.MiscParser
import edu.slapoguzov.emodetect.core.conll.misc.SimpleMiscParser

class DefaultConllParser : ConllParser {

    private val miscParsers = mutableListOf<MiscParser<*>>(SimpleMiscParser())

    override fun addMiscParse(miscParser: MiscParser<*>) {
        miscParsers += miscParser
    }

    override fun parse(text: String): Conll {
        val tabbed = text.replace(Regex("[ ]{2,}"), "\t")
        val rows = tabbed.split("\n").map { parseRow(it) }
        return Conll(rows)
    }

    private fun parseRow(text: String): ConllRow {
        val groups = text.split(DELIMITER)
        val numberEmptyGroups = NUMBER_GROUPS - groups.size
        val fullGroups = when {
            numberEmptyGroups > 0 -> groups + arrayOfNulls<String>(numberEmptyGroups)
            else -> groups
        }

        val tokens = fullGroups.map {
            if (it == EMPTY_FIELD) null else it
        }
        return ConllRow(
                id = tokens[0]?.toInt() ?: throw IllegalArgumentException("id must not be null"),
                form = tokens[1]!!,
                lemma = tokens[2],
                upos = tokens[3]?.let { UniversalPos.parse(it) },
                xpos = tokens[4],
                feats = tokens[5]?.let { parseFeats(it) },
                head = tokens[6],
                depRel = tokens[7]?.let { DependencyRelation.parse(it) },
                deps = tokens[8]?.toInt(),
                misc = tokens[9]?.let { parseMiscs(it) }
        )
    }

    private fun parseFeats(text: String): MutableSet<Feat> {
        return text
                .split(FEAT_DELIMITER)
                .mapNotNull { feat -> Feats.values().find { feat.contains(it.token) } ?: UnknownFeat(feat) }
                .toMutableSet()
    }

    private fun parseMiscs(text: String): MutableSet<Misc> {
        return text
                .split(MISC_DELIMITER)
                .mapNotNull { misc -> miscParsers.find { it.isSuitable(misc) }?.let { it.parse(misc) } }
                .toMutableSet()
    }

    companion object {
        private val NUMBER_GROUPS = 10
    }
}
