package edu.slapoguzov.emodetect.core.conll

import edu.slapoguzov.emodetect.core.conll.misc.MiscParser

interface ConllParser {
    fun parse(text: String): Conll
    fun addMiscParse(miscParser: MiscParser<*>)
}
