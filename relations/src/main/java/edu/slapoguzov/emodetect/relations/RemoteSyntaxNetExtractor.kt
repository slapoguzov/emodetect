package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.morpho.AdditionalMorphoDictionary
import edu.slapoguzov.emodetect.morpho.MorphoProcessor
import edu.slapoguzov.emodetect.morpho.mystem.MyStemFactory
import edu.slapoguzov.emodetect.relations.model.connl.ConnlSentence
import mu.KLogging
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.net.SocketException


class RemoteSyntaxNetExtractor(
        private val host: String = "localhost",
        private val port: Int = 8111
) : RelationsExtractor {

    private val myStem = MyStemFactory().getMyStem()
    private val additionalMorphoDictionary = AdditionalMorphoDictionary()
    private val morphoProcessor = MorphoProcessor(myStem, additionalMorphoDictionary)
    private val connlReader = ConnlReader()
    private var socket = Socket(host, port)

    override fun extract(text: String): ConnlSentence {
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
            val connlRows = connlReader.readSourceLines(connlText)
            ConnlEnricher.enrich(connlRows, morphoUnits)
            val connlSentence = connlReader.read(connlRows)
            logger.info { "connlSentence: $connlSentence" }
            return connlSentence
        } catch (e: SocketException) {
            socket.close()
            socket = Socket(host, port)
            throw e
        }
    }


    companion object : KLogging() {
        private val END_COMMAND = "\n\n".toByteArray(Charsets.UTF_8)
    }
}