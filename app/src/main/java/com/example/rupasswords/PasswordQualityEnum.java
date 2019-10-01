package com.example.rupasswords;

public enum PasswordQualityEnum {

    VERY_BAD("Очень слабый", 0),
    BAD("Слабоватый", 1),
    NORMAL("Нормальный", 2),
    GOOD("Хороший", 3),
    VERY_GOOD("Очень хороший", 4),
    EXCELLENT("Отличный!", 5),
    ;

    public String getQualityString() {
        return qualityString;
    }

    public int getQualityLevel() {
        return qualityLevel;
    }

    private final String qualityString;
    private final int qualityLevel;

    PasswordQualityEnum(String qualityString, int qualityLevel) {
        this.qualityString = qualityString;
        this.qualityLevel = qualityLevel;
    }



}
