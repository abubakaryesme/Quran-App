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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<SurahRecord> surahList;

    public RecyclerViewAdapter(Context context,List<SurahRecord> surahList)
    {
        this.context = context;
        this.surahList = surahList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.contentVerse = surahList.get(position);

        holder.suraahName.setText(String.valueOf(holder.contentVerse.getsurahName()));
        holder.suraahNumber.setText(String.valueOf(holder.contentVerse.getsurahNumber()));
        holder.suraahTranslation.setText(String.valueOf(holder.contentVerse.getsurahNameTranslation()));

    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView suraahName;
        public TextView suraahNumber;
        public TextView suraahTranslation;
        SurahRecord contentVerse;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);

            this.suraahName= itemView.findViewById(R.id.txt_surah_name);
            this.suraahNumber = itemView.findViewById(R.id.txt_surah_no);
            this.suraahTranslation = itemView.findViewById(R.id.txt_surah_translation);
        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();
            int surahNo = surahList.get(position).getsurahNumber();
            Toast.makeText(context, "suraah # "+surahNo, Toast.LENGTH_SHORT).show();
        }

    }
}
