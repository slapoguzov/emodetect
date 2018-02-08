package edu.slapoguzov.emodetect.sense.net.api.model;

public class Subject extends CollocationUnit {
    private SubjectType subjectType;

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }
}
