package edu.slapoguzov.emodetect.relations

import edu.slapoguzov.emodetect.core.getPathToResource

class RelationExtractorFactory {
    private val parserModelPath = this.javaClass.getPathToResource("nndep.rus.model90.9_88.6.txt.gz")
    private val taggerPath = this.javaClass.getPathToResource("russian-ud-pos.tagger")
    private val mfPath = this.javaClass.getPathToResource("russian-ud-mf.tagger")
    private val dictPath = this.javaClass.getPathToResource("dict.tsv")

    private val relationExtractor = RelationExtractor(parserModelPath, taggerPath, mfPath, dictPath)

    fun getInstance(): RelationExtractor {
        return relationExtractor
    }
}