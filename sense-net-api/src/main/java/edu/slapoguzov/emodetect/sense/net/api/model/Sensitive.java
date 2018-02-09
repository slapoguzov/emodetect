package edu.slapoguzov.emodetect.sense.net.api.model;

public abstract class Sensitive {
    /**
     * Положительно или отрицательно число
     * в диапозоне от -1 до 1, которое соответствует
     * отрицательно или положительной эмоции
     */
    private Double valence;

    public Double getValence() {
        return valence;
    }

    public void setValence(Double valence) {
        this.valence = valence;
    }
}
