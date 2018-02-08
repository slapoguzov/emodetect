package edu.slapoguzov.emodetect.sense.net.api.model;

public class Sentence implements Sensitive {
    private Double valence;
    private Collocation collocation;

    public Double getValence() {
        return this.valence;
    }

    public void setValence(Double valence) {
        this.valence = valence;
    }

    public Collocation getCollocation() {
        return collocation;
    }

    public void setCollocation(Collocation collocation) {
        this.collocation = collocation;
    }
}
