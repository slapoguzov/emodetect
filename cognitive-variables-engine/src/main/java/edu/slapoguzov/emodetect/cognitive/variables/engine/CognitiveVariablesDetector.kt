package edu.slapoguzov.emodetect.cognitive.variables.engine

import edu.slapoguzov.emodetect.occ.model.variables.CognitiveVariable
import edu.slapoguzov.emodetect.sentence.model.Sentence

interface CognitiveVariablesDetector {
    fun detect(sentence: Sentence): Set<CognitiveVariable>
}