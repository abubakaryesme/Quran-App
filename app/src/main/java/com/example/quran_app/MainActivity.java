package com.example.quran_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    Spinner parahSpinner;
    ArrayAdapter<String> spinnerAdapter;

    Button btn_open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //DropDown List:
        List<String> parah = new ArrayList<>();
        for(int i =1; i<=30;i++)
            parah.add("Parah # " + i);
        //Adding items DropDown:
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, parah);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parahSpinner = findViewById(R.id.parah_spinner);
        parahSpinner.setAdapter(spinnerAdapter);

        btn_open=findViewById(R.id.btn_open);

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String surahStr =  parahSpinner.getSelectedItem().toString();
                String siparaCoversion[]=surahStr.split("#");
                siparaCoversion[1] = siparaCoversion[1].trim();
                Integer surahStrToI = Integer.valueOf(siparaCoversion[1]);
                Toast.makeText(MainActivity.this, siparaCoversion[1], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("surah_number", String.valueOf(surahStrToI));
                startActivity(intent);
            }
        });


        JsonConverter dataQuran = new JsonConverter(this);
        ArrayList<SurahRecord> surahRecords = dataQuran.surahRecords;

        recyclerView = findViewById(R.id.recycler_view_surah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, surahRecords, dataQuran);
        recyclerView.setAdapter(recyclerViewAdapter);


    }
}