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
    List<SurahRecord> surahRecords;
    List<VerseArray> verseContent;
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
            int lengthOfArray = verseArray.length();

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

}
