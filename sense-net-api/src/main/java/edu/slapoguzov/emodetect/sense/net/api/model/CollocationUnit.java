package edu.slapoguzov.emodetect.sense.net.api.model;

import java.util.List;

public abstract class CollocationUnit extends Sensitive {
    private List<Word> words;
    private List<CollocationUnit> relations;

    private long numberPositiveSenses;
    private long numberNegativeSenses;

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<CollocationUnit> getRelations() {
        return relations;
    }

    public void setRelations(List<CollocationUnit> relations) {
        this.relations = relations;
    }

    public long getNumberPositiveSenses() {
        return numberPositiveSenses;
    }

    public void setNumberPositiveSenses(long numberPositiveSenses) {
        this.numberPositiveSenses = numberPositiveSenses;
    }

    public long getNumberNegativeSenses() {
        return numberNegativeSenses;
    }

    public void setNumberNegativeSenses(long numberNegativeSenses) {
        this.numberNegativeSenses = numberNegativeSenses;
    }
}
