package edu.slapoguzov.emodetect.core.conll.misc

interface MiscParser<T : Misc> {
    fun parse(text: String): T

    fun isSuitable(text: String): Boolean
}
