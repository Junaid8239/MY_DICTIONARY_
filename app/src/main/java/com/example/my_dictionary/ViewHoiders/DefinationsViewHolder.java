package com.example.my_dictionary.ViewHoiders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_dictionary.R;

public class DefinationsViewHolder extends RecyclerView.ViewHolder {
    public  TextView textView_definition;
    public  TextView textView_example;
    public  TextView textView_synonyms;
    public  TextView textView_antonyms;

    public DefinationsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_definition=itemView.findViewById(R.id.textView_definition);
        textView_example=itemView.findViewById(R.id.textView_example);
        textView_synonyms=itemView.findViewById(R.id.textView_synonyms);
        textView_antonyms=itemView.findViewById(R.id.textView_antonyms);
    }
}
