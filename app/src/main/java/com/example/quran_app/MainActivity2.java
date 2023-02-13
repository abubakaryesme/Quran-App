package com.example.quran_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerViewVerse;
    private RecyclerViewVerseAdapter recyclerViewVerseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_content);

        Intent intent = getIntent();
        String surahInStr = intent.getStringExtra("surah_number");
        int surahNo = Integer.parseInt(surahInStr);
        List<VerseArray> verseRecords;
        JsonConverter dataQuran = new JsonConverter(this);
        if(surahNo > 30)
        {
            verseRecords = dataQuran.setContentSurah(surahNo -30, "UrduLanguage");
        }
        else
        {
            verseRecords = dataQuran.setContentParah(surahNo -30, "UrduLanguage");
        }

        recyclerViewVerse = findViewById(R.id.recycler_view_verse);
        recyclerViewVerse.setHasFixedSize(true);
        recyclerViewVerse.setLayoutManager(new LinearLayoutManager(MainActivity2.this));

        recyclerViewVerseAdapter = new RecyclerViewVerseAdapter(MainActivity2.this, verseRecords);
        recyclerViewVerse.setAdapter(recyclerViewVerseAdapter);

    }
}