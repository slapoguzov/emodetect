package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.relations.model.connl.ConnlSentence
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.net.SocketException
import kotlin.coroutines.experimental.suspendCoroutine


class RemoteSyntaxNetExtractor(
        private val host: String = "localhost",
        private val port: Int = 8111
) : RelationsExtractor {

    private val connlReader = ConnlReader()
    var socket = Socket(host, port)

    override fun extract(text: String): ConnlSentence {
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
            return connlReader.read(connlText)
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