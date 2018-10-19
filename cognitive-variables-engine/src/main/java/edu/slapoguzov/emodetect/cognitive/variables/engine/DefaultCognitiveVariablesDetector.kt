package edu.slapoguzov.emodetect.cognitive.variables.engine

import edu.slapoguzov.emodetect.cognitive.variables.engine.detectors.AgentFondnessDetector
import edu.slapoguzov.emodetect.cognitive.variables.engine.detectors.ObjectFondnessDetector
import edu.slapoguzov.emodetect.occ.model.variables.CognitiveVariable
import edu.slapoguzov.emodetect.sentence.model.Sentence

class DefaultCognitiveVariablesDetector : CognitiveVariablesDetector {

    override fun detect(sentence: Sentence): Set<CognitiveVariable> {
        return detectors.map { it.detect(sentence) }.toSet()
    }

    private val detectors = listOf(
            AgentFondnessDetector(),
            ObjectFondnessDetector()
    )
}