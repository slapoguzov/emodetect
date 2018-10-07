package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.morpho.model.MorphoUnit
import edu.slapoguzov.emodetect.relations.model.connl.*

object ConnlEnricher {

    fun enrich(rows: List<ConnlRow>, morphoUnits: Map<Int, MorphoUnit> = emptyMap()): List<ConnlRow> {
        rows.forEach {
            val morphoUnit = morphoUnits[it.id.toInt()] ?: return@forEach
            it.lemma = it.lemma ?: morphoUnit.lex
            it.misc = morphoUnit.grammems.joinToString(",")
        }
        return rows
    }
}