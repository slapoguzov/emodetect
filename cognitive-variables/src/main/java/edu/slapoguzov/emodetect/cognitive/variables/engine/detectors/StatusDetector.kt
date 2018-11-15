package edu.slapoguzov.emodetect.cognitive.variables.engine.detectors

import edu.slapoguzov.emodetect.occ.model.variables.Status
import edu.slapoguzov.emodetect.sentence.model.Characteristic
import edu.slapoguzov.emodetect.sentence.model.Characteristic.*
import edu.slapoguzov.emodetect.sentence.model.Sentence

class StatusDetector : VariableDetector<Status> {
    override fun detect(sentence: Sentence): Status? {
        val actions = sentence.allWords
                .filter { it.characteristics.contains(IS_ACTION) }
        val (pastActions, nounPastActions) = actions
                .partition { it.characteristics.contains(PAST_TENSE) }
        val modal = sentence.allWords.filter { it.characteristics.contains(IS_MODAL) }
        if (nounPastActions.isNotEmpty() || modal.isNotEmpty()) return Status.UNCONFIRMED
        if (pastActions.isNotEmpty()
                && pastActions.any { it.characteristics.contains(NEGATION) }
                && pastActions.map { it.valence }.average() < 0.0) return Status.DISCONFIRMED
        return Status.CONFIRMED
    }
}