package edu.slapoguzov.emodetect.sense.net.api;

import edu.slapoguzov.emodetect.sense.net.api.model.Sentence;

public interface SenseNetService {
    Sentence parseSentence(String text);
}
