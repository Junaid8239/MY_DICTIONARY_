package com.example.my_dictionary.ViewHoiders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_dictionary.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {
    public  TextView partsOfSpeech;
    public  RecyclerView recycler_definitions;
    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);
        partsOfSpeech = itemView.findViewById(R.id.partsOfSpeech);
        recycler_definitions = itemView.findViewById(R.id.recycler_definitions);

    }
}
