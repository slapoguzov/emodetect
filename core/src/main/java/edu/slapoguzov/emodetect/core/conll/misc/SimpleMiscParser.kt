package edu.slapoguzov.emodetect.core.conll.misc

class SimpleMiscParser : MiscParser<SimpleMisc> {

    override fun parse(text: String): SimpleMisc {
        val emotionType = text.split(SimpleMisc.KEY)
        return SimpleMisc(emotionType[0], emotionType[1])
    }

    override fun isSuitable(text: String): Boolean {
        return text.contains(SimpleMisc.KEY)
    }


}
