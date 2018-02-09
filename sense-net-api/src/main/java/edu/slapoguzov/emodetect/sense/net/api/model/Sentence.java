package edu.slapoguzov.emodetect.sense.net.api.model;

public class Sentence extends Sensitive {
    private Double valence;
    private Collocation collocation;

    public Collocation getCollocation() {
        return collocation;
    }

    public void setCollocation(Collocation collocation) {
        this.collocation = collocation;
    }
}
