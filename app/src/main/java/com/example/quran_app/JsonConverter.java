package com.example.quran_app;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonConverter {

    JSONArray verseArray;
    int lengthOfArray;
    ArrayList<SurahRecord> surahRecords;
    ArrayList<VerseArray> verseContent;
    Context context;

    public JsonConverter(Context context)
    {
        this.context=context;
        ArrayList<Integer> surahNumbers = new ArrayList<>();
        surahRecords = new ArrayList<SurahRecord>();

        try {
            InputStream inputStream = context.getAssets().open("QuranMetaData.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            verseArray = new JSONArray(builder.toString());
            lengthOfArray = verseArray.length();

            for (int i = 0; i < lengthOfArray; i++)
            {
                JSONObject jsonObject = verseArray.getJSONObject(i);
                if(surahRecords.size() == 0)
                {
                    surahRecords.add(new SurahRecord(jsonObject.getInt("surah_number"),
                            jsonObject.getString("surah_name"),
                            jsonObject.getString("englishNameTranslation")));
                    surahNumbers.add(jsonObject.getInt("surah_number"));
                }
                else
                {
                    if (!surahNumbers.contains(jsonObject.getInt("surah_number")))
                    {
                        surahRecords.add(new SurahRecord(jsonObject.getInt("surah_number"),
                                jsonObject.getString("surah_name"),
                                jsonObject.getString("englishNameTranslation")));
                        surahNumbers.add(jsonObject.getInt("surah_number"));
                    }
                }
            }
        } catch (IOException e) {
            Log.e("File Error", "Error reading file: " + e.getMessage());
        } catch (JSONException e) {
            Log.e("JSON Error", "Error parsing JSON object: " + e.getMessage());
        }
    }

    public List<VerseArray> setContentParah(int parahNumber, String translationLanguage)
    {
        translationLanguage = "UrduTranslation";
        verseContent.clear();
        try {
            for (int i = 0; i < lengthOfArray; i++) {
                JSONObject jsonObject = verseArray.getJSONObject(i);
                if(jsonObject.getInt("juz") == parahNumber) {
                    verseContent.add(new VerseArray(jsonObject.getInt("number"),
                            jsonObject.getString("text"),
                            jsonObject.getString("surah_name"),
                            jsonObject.getString(translationLanguage)));
                }
            }
        } catch (JSONException e) {
            Log.e("JSON Error", "Error parsing JSON object: " + e.getMessage());
        }
        return verseContent;
    }

    public List<VerseArray> setContentSurah(int surahNumber, String translationLanguage)
    {
        translationLanguage = "UrduTranslation";

        verseContent.clear();
        try {
            for (int i = 0; i < lengthOfArray; i++) {
                JSONObject jsonObject = verseArray.getJSONObject(i);
                if(jsonObject.getInt("surah_number") == surahNumber) {
                    verseContent.add(new VerseArray(jsonObject.getInt("number"),
                            jsonObject.getString("text"),
                            jsonObject.getString("surah_name"),
                            jsonObject.getString(translationLanguage)));
                }
            }
        } catch (JSONException e) {
            Log.e("JSON Error", "Error parsing JSON object: " + e.getMessage());
        }
        return verseContent;
    }
}
