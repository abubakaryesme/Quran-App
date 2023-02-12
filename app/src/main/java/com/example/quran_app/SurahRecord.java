package com.example.quran_app;

public class SurahRecord {
    private Integer surahNumber;
    private String surahName;
    private String surahNameTranslation;

    public SurahRecord(Integer surahNumber,String surahName, String surahNameTranslation)
    {
        this.surahName=surahName;
        this.surahNumber = surahNumber;
        this.surahNameTranslation = surahNameTranslation;
    }

    public Integer getsurahNumber() {
        return surahNumber;
    }

    public void setsurahNumber(Integer surahNumber) {
        this.surahNumber = surahNumber;
    }

    public String getsurahName() {
        return surahName;
    }

    public void setsurahName(String surahNumber) {
        this.surahName = surahName;
    }

    public String getsurahNameTranslation() {
        return surahNameTranslation;
    }

    public void setsurahNameTranslation(String surahNumberTranslation) {
        this.surahNameTranslation = surahNameTranslation;
    }
}
