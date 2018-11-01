package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.CognitiveVariable
import edu.slapoguzov.emodetect.sentence.model.Sentence

interface VariableDetector<T : CognitiveVariable> {
    fun detect(sentence: Sentence): T?
}