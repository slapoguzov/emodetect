package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.morpho.mystem.MyStemFactory
import edu.slapoguzov.emodetect.relations.model.connl.ConnlSentence
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.net.SocketException


class RemoteSyntaxNetExtractor(
        private val host: String = "localhost",
        private val port: Int = 8111
) : RelationsExtractor {

    private val myStem = MyStemFactory().getMyStem()

    private val morphoProcessor = MorphoProcessor(myStem)
    private val connlReader = ConnlReader()
    private var socket = Socket(host, port)

    override fun extract(text: String): ConnlSentence {
        val morphoUnits = morphoProcessor.process(text)
        var finalText = text.replace(",", " , ").replace(".", " . ")
        if (!finalText.endsWith(" . ")) {
            finalText += " . "
        }
        val bytes = finalText.toByteArray(Charsets.UTF_8) + END_COMMAND

        try {
            socket.getOutputStream().write(bytes)
            socket.getOutputStream().flush()

            val input = BufferedReader(InputStreamReader(socket.getInputStream()))
            val connlText = input.readText()
            val connlRows = connlReader.readSourceLines(connlText)
            ConnlEnricher.enrich(connlRows, morphoUnits)
            return connlReader.read(connlRows)
        } catch (e: SocketException) {
            socket.close()
            socket = Socket(host, port)
            throw e
        }
    }


    companion object {
        private val END_COMMAND = "\n\n".toByteArray(Charsets.UTF_8)
    }
}