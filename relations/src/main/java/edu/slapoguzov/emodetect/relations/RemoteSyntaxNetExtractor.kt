package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.relations.model.connl.ConnlSentence

class RemoteSyntaxNetExtractor : RelationsExtractor {

    private val connlReader = ConnlReader()

    val stub = "1       Внезапно        _       ADV     _       Degree=Pos|fPOS=ADV++   3       advmod  _       _\n" +
            "2       я       _       PRON    _       fPOS=PRON++     3       nsubj   _       _\n" +
            "3       нашел   _       VERB    _       Aspect=Perf|Gender=Masc|Mood=Ind|Number=Sing|Tense=Past|VerbForm=Fin|Voice=Act|fPOS=VERB++      0       ROOT    _       _\n" +
            "4       его     _       PRON    _       fPOS=PRON++     3       dobj    _       _\n" +
            "5       в       _       ADP     _       fPOS=ADP++      6       case    _       _\n" +
            "6       самолете        _       NOUN    _       Animacy=Inan|Case=Loc|Gender=Masc|Number=Sing|fPOS=NOUN++       3       nmod    _       _\n" +
            "7       ,       _       PUNCT   ,       fPOS=PUNCT++,   6       punct   _       _\n" +
            "8       но      _       CONJ    _       fPOS=CONJ++     3       cc      _       _\n" +
            "9       он      _       PRON    _       fPOS=PRON++     11      nsubj   _       _\n" +
            "10      был     _       AUX     _       Aspect=Imp|Gender=Masc|Mood=Ind|Number=Sing|Tense=Past|VerbForm=Fin|Voice=Act|fPOS=AUX++        11      cop     _       _\n" +
            "11      пьян    _       ADJ     _       Degree=Pos|Gender=Masc|Number=Sing|Variant=Brev|fPOS=ADJ++      3       conj    _       _\n" +
            "12      .       _       PUNCT   .       fPOS=PUNCT++.   3       punct   _       _\n"

    override fun extract(text: String): ConnlSentence {
        return connlReader.read(stub)
    }
}