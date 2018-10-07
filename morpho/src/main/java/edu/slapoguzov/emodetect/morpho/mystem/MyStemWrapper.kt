package edu.slapoguzov.emodetect.morpho.mystem

import com.fasterxml.jackson.databind.ObjectMapper
import edu.slapoguzov.emodetect.morpho.mystem.model.StemGrammem
import edu.slapoguzov.emodetect.morpho.mystem.model.StemUnit
import ru.stachek66.nlp.mystem.holding.MyStem
import ru.stachek66.nlp.mystem.holding.Request
import ru.stachek66.nlp.mystem.model.Info
import scala.collection.JavaConversions

class MyStemWrapper(private val myStem: MyStem) {

    private val mapper = ObjectMapper()

    fun analyze(text: String): Map<Int, StemUnit> {
        val infoList = JavaConversions.asJavaIterable(
                myStem.analyze(Request(text))
                        .info()
                        .toIterable()
        )
        return infoList.mapIndexed { index, it ->  index + 1 to it.convert() }.toMap()
    }

    private fun Info.convert(): StemUnit {
        val rawResponse = mapper.readValue(this.rawResponse(), Map::class.java) as Map<String, List<Map<String, String>>>
        val grammes = rawResponse.get("analysis")?.firstOrNull()?.get("gr")
                ?.split(",")
                ?.mapNotNull { StemGrammem.fromText(it) }
                .orEmpty()
        return StemUnit(
                this.lex().get(),
                this.initial(),
                grammes
        )
    }
}