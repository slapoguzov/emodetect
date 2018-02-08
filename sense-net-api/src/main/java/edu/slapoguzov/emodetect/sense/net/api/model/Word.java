package edu.slapoguzov.emodetect.sense.net.api.model;

import java.util.List;

public class Word {
    private String text;
    private Boolean isNegation;
    private List<MorphoCharacteristic> morphoCharacteristics;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getNegation() {
        return isNegation;
    }

    public void setNegation(Boolean negation) {
        isNegation = negation;
    }

    public List<MorphoCharacteristic> getMorphoCharacteristics() {
        return morphoCharacteristics;
    }

    public void setMorphoCharacteristics(List<MorphoCharacteristic> morphoCharacteristics) {
        this.morphoCharacteristics = morphoCharacteristics;
    }
}
