package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.core.conll.Conll

interface RelationsExtractor {
    fun extract(text: String): Conll
}