package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.core.conll.Conll
import edu.slapoguzov.emodetect.core.conll.DefaultConllParser
import edu.slapoguzov.emodetect.core.conll.Feats
import edu.slapoguzov.emodetect.morpho.AdditionalMorphoDictionary
import edu.slapoguzov.emodetect.morpho.MorphoProcessor
import edu.slapoguzov.emodetect.morpho.model.Grammem
import edu.slapoguzov.emodetect.morpho.model.MorphoUnit
import edu.slapoguzov.emodetect.morpho.mystem.MyStemFactory
import mu.KLogging
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.net.SocketException


class RemoteSyntaxNetExtractor(
        private val host: String = "195.201.42.18",
        private val port: Int = 8111,
        private val pathToMystem: String = "libs/mystem-3.1.exe"
) : RelationsExtractor {

    private val myStem = MyStemFactory(pathToMystem).getMyStem()
    private val additionalMorphoDictionary = AdditionalMorphoDictionary()
    private val morphoProcessor = MorphoProcessor(myStem, additionalMorphoDictionary)
    private val conllParser = DefaultConllParser()
    private var socket = Socket(host, port)

    override fun extract(text: String): Conll {
        val morphoUnits = morphoProcessor.process(text)
        var finalText = text.replace(Regex("(.)(\\p{Punct}|[\\(\\)])(.)")) { r ->
            r.groupValues[1].trim() + " " + r.groupValues[2] + " " + r.groupValues[3].trim()
        }
        if (!finalText.endsWith(" . ")) {
            finalText += " . "
        }
        val bytes = finalText.toByteArray(Charsets.UTF_8) + END_COMMAND

        try {
            Thread.sleep(1000)
            socket.getOutputStream().write(bytes)
            Thread.sleep(1000)
            socket.getOutputStream().flush()
            Thread.sleep(1000)

            val input = BufferedReader(InputStreamReader(socket.getInputStream()))
            val connlText = input.readText()
            val conll = conllParser.parse(connlText)
            enrich(conll, morphoUnits)
            logger.info { "$conll" }
            return conll
        } catch (e: SocketException) {
            socket.close()
            socket = Socket(host, port)
            throw e
        }
    }

    fun enrich(conll: Conll, morphoUnits: Map<Int, MorphoUnit> = emptyMap()) {
        conll.rows.forEach { row ->
            val morphoUnit = morphoUnits[row.id]
            row.lemma = row.lemma ?: morphoUnit?.lex
            val currentFeats = row.feats ?: mutableSetOf()
            val newFeats = morphoUnit?.grammems
                    ?.mapNotNull { it.toFeat() }
                    ?.filter { !currentFeats.contains(it) }
                    .orEmpty()

            //override modal
            val isMorphoUnitContainModal = morphoUnit?.grammems?.any { it == Grammem.MODAL } ?: false
            val isCurrentContainModal = row.feats?.contains(Feats.MODAL) ?: false
            if (isCurrentContainModal && !isMorphoUnitContainModal) {
                row.feats?.remove(Feats.MODAL)
            }

            currentFeats.addAll(newFeats)
        }
    }

    private fun Grammem.toFeat(): Feats? {
        return when (this) {
            Grammem.PAST_TENSE -> Feats.PAST_TENSE
            Grammem.PRESENT_TENSE -> Feats.PRESENT_TENSE
            Grammem.MODAL -> Feats.MODAL
            Grammem.UNEXPECTED -> Feats.UNEXPECTED
            Grammem.ANIMATE -> Feats.ANIMATE
            Grammem.INANIMATE -> Feats.INANIMATE
            Grammem.FIRST_PERSON -> Feats.FIRST_PERSON
            else -> null
        }
    }

    companion object : KLogging() {
        private val END_COMMAND = "\n\n".toByteArray(Charsets.UTF_8)
    }
}