package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.relations.model.connl.*
import edu.slapoguzov.emodetect.sentence.mystem.model.StemUnit

object ConnlEnricher {

    fun enrich(rows: List<ConnlRow>, morphoUnits: Map<Int, StemUnit> = emptyMap()): List<ConnlRow> {
        rows.forEach {
            val stemUnit = morphoUnits[it.id.toInt()] ?: return@forEach
            it.lemma = it.lemma ?: stemUnit.lex
        }
        return rows
    }
}