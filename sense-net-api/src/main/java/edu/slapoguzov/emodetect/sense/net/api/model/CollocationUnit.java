package edu.slapoguzov.emodetect.sense.net.api.model;

import java.util.List;

public abstract class CollocationUnit implements Sensitive {
    private List<Word> words;
    private List<CollocationUnit> relations;

    private Double valence;
    private Long numberPositiveSenses;
    private Long numberNegativeSenses;

    public Double getValence() {
        return valence;
    }

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

    public void setValence(Double valence) {
        this.valence = valence;
    }

    public Long getNumberPositiveSenses() {
        return numberPositiveSenses;
    }

    public void setNumberPositiveSenses(Long numberPositiveSenses) {
        this.numberPositiveSenses = numberPositiveSenses;
    }

    public Long getNumberNegativeSenses() {
        return numberNegativeSenses;
    }

    public void setNumberNegativeSenses(Long numberNegativeSenses) {
        this.numberNegativeSenses = numberNegativeSenses;
    }
}
