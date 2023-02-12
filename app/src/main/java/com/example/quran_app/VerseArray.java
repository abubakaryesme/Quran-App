package com.example.quran_app;

public class VerseArray {
    private Integer lineNumber;
    private String verse;
    private String verseTranslation;
    private String surahName;

    public VerseArray(Integer lineNumber,String verse,String surahName, String verseTranslation)
    {
        this.verse=verse;
        this.lineNumber = lineNumber;
        this.surahName = surahName;
        this.verseTranslation = verseTranslation;
    }

    public String getsurahName() {
        return surahName;
    }

    public void setsurahName(String surahNumber) {
        this.surahName = surahName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getverse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public String getVerseTranslation() {
        return verseTranslation;
    }

    public void setVerseTranslation(String verseTranslation) {
        this.verseTranslation = verseTranslation;
    }
}
