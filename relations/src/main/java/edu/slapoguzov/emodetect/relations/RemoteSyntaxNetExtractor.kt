package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.relations.model.connl.ConnlSentence
import org.apache.commons.net.telnet.TelnetClient
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import java.net.Socket
import java.io.InputStreamReader
import java.io.BufferedReader
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import java.io.PrintWriter
import edu.stanford.nlp.io.EncodingPrintWriter.out
import java.nio.ByteBuffer
import java.nio.charset.Charset


class RemoteSyntaxNetExtractor(
        host: String = "localhost",
        port: Int = 8111
) : RelationsExtractor {

    private val connlReader = ConnlReader()
    //private val telnet = TelnetClient()

    init {
        //telnet.connect(host, port)
    }

    private val stub = "1       Внезапно        _       ADV     _       Degree=Pos|fPOS=ADV++   3      advmod   _       _\n" +
            "2       я       _       PRON    _       fPOS=PRON++     3       nsubj   _      _\n" +
            "3       нашел   _       VERB    _       Aspect=Perf|Gender=Masc|Mood=Ind|Number=Sing|Tense=Past|VerbForm=Fin|Voice=Act|fPOS=VERB++      0       ROOT    _      _\n" +
            "4       его     _       PRON    _       fPOS=PRON++     3       dobj    _      _\n" +
            "5       в       _       ADP     _       fPOS=ADP++      6       case    _      _\n" +
            "6       самолете        _       NOUN    _       Animacy=Inan|Case=Loc|Gender=Masc|Number=Sing|fPOS=NOUN++       3       nmod    _       _\n" +
            "7       .       _       PUNCT   .       fPOS=PUNCT++.   3       punct   _      _"

    override fun extract(text: String): ConnlSentence {
        val array = "Мама мыла\n\n"
        val bytes = array.toByteArray()
        val socket = Socket("localhost", 8111)
        socket.getOutputStream().write(bytes)
        val input = BufferedReader(InputStreamReader(socket.getInputStream()))
        val readLines = input.readLines()
        return connlReader.read(text)
    }
}