package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.relations.model.connl.ConnlSentence

interface RelationsExtractor {
    fun extract(text: String): ConnlSentence
}