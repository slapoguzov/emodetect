package edu.slapoguzov.emodetect.sense.net.api.model;

public interface Sensitive {
    /**
     * Возвращает положительно или отрицательно число
     * в диапозоне от -1 до 1, которое соответствует
     * отрицательно или положительной эмоции
     */
    Double getValence();
}
