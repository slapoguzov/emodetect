package edu.slapoguzov.emodetect.sense.net.api.model;

public class CollocationRelation {
    private Collocation collocation;
    private CollocationRelationType type;

    public Collocation getCollocation() {
        return collocation;
    }

    public void setCollocation(Collocation collocation) {
        this.collocation = collocation;
    }

    public CollocationRelationType getType() {
        return type;
    }

    public void setType(CollocationRelationType type) {
        this.type = type;
    }
}
