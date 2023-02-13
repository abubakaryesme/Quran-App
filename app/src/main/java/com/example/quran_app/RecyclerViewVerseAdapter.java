package com.example.quran_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewVerseAdapter extends RecyclerView.Adapter<RecyclerViewVerseAdapter.ViewHolder> {
    private Context context;
    private List<VerseArray> verseList;

    public RecyclerViewVerseAdapter(Context context,List<VerseArray> surahList)
    {
        this.context = context;
        this.verseList = surahList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quran_verse,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.contentVerse = verseList.get(position);

        holder.verseText.setText(String.valueOf(holder.contentVerse.getverse()));
        holder.verseTranslation.setText(String.valueOf(holder.contentVerse.getVerseTranslation()));

    }

    @Override
    public int getItemCount() {
        return verseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView verseText;
        public TextView verseTranslation;
        VerseArray contentVerse;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.verseText= itemView.findViewById(R.id.txt_verse);
            this.verseTranslation = itemView.findViewById(R.id.txt_verse_translation);
        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();
            String surahName = verseList.get(position).getsurahName();
            Toast.makeText(context, surahName, Toast.LENGTH_SHORT).show();

        }

    }
}