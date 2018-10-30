package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.morpho.model.Grammem
import edu.slapoguzov.emodetect.morpho.model.Grammem.*
import edu.slapoguzov.emodetect.morpho.model.MorphoUnit
import edu.slapoguzov.emodetect.relations.model.connl.*

object ConnlEnricher {

    fun enrich(rows: List<ConnlRow>, morphoUnits: Map<Int, MorphoUnit> = emptyMap()): List<ConnlRow> {
        rows.forEach { row ->
            val morphoUnit = morphoUnits[row.id.toInt()]
            row.lemma = row.lemma ?: morphoUnit?.lex
            val currentFeats = row.feats ?: ""
            val newFeats = morphoUnit?.grammems
                    ?.mapNotNull { it.toFeat() }
                    ?.filter { !currentFeats.contains(it.token) }
                    .orEmpty()

            //override modal
            val isMorphoUnitContainModal = morphoUnit?.grammems?.any { it == MODAL } ?: false
            val isCurrentContainModal = row.feats?.contains(Feats.MODAL.token) ?: false
            if(isCurrentContainModal && !isMorphoUnitContainModal) {
                row.feats = row.feats?.replace(Regex("Mood=.*?\\|"), "")
            }

            if (newFeats.isEmpty()) return@forEach
            val strNewFeats = newFeats.joinToString("|") { it.token }

            if (currentFeats.isEmpty()) row.feats = strNewFeats
            else row.feats = "$currentFeats|$strNewFeats"

        }
        return rows
    }

    private fun Grammem.toFeat(): Feats? {
        return when (this) {
            PAST_TENSE -> Feats.PAST_TENSE
            PRESENT_TENSE -> Feats.PRESENT_TENSE
            MODAL -> Feats.MODAL
            UNEXPECTED -> Feats.UNEXPECTED
            else -> null
        }
    }

}