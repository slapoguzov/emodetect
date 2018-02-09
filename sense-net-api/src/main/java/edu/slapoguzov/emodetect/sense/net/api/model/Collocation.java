package edu.slapoguzov.emodetect.sense.net.api.model;

import java.util.List;

public class Collocation extends Sensitive {
    private CollocationUnit action;
    private CollocationUnit subject;
    private CollocationUnit object;

    private List<CollocationRelation> relations;

    public CollocationUnit getAction() {
        return action;
    }

    public void setAction(CollocationUnit action) {
        this.action = action;
    }

    public CollocationUnit getSubject() {
        return subject;
    }

    public void setSubject(CollocationUnit subject) {
        this.subject = subject;
    }

    public CollocationUnit getObject() {
        return object;
    }

    public void setObject(CollocationUnit object) {
        this.object = object;
    }

    public List<CollocationRelation> getRelations() {
        return relations;
    }

    public void setRelations(List<CollocationRelation> relations) {
        this.relations = relations;
    }

}
