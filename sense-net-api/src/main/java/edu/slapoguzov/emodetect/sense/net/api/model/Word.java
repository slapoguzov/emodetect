package edu.slapoguzov.emodetect.sense.net.api.model;

import java.util.List;

public class Word {
    private String text;
    private boolean isNegation;
    private List<MorphoCharacteristic> morphoCharacteristics;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getNegation() {
        return isNegation;
    }

    public void setNegation(boolean negation) {
        isNegation = negation;
    }

    public List<MorphoCharacteristic> getMorphoCharacteristics() {
        return morphoCharacteristics;
    }

    public void setMorphoCharacteristics(List<MorphoCharacteristic> morphoCharacteristics) {
        this.morphoCharacteristics = morphoCharacteristics;
    }
}
