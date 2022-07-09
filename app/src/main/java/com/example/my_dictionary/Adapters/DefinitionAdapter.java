package com.example.my_dictionary.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_dictionary.Models.Definitions;
import com.example.my_dictionary.R;
import com.example.my_dictionary.ViewHoiders.DefinationsViewHolder;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinationsViewHolder> {
    private  Context context;
    private  List<Definitions> definitionsList;

    public DefinitionAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }

    @NonNull
    @Override
    public DefinationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinationsViewHolder(LayoutInflater.from(context).inflate(R.layout.definitions_list_items,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull DefinationsViewHolder holder, int position) {
        holder.textView_definition.setText("Definition "+ definitionsList.get(position).getDefinition());
        holder.textView_example.setText("Example:" +definitionsList.get(position).getExample());
        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.textView_synonyms.setText(synonyms);
        holder.textView_antonyms.setText(antonyms);

        holder.textView_synonyms.setSelected(true);
        holder.textView_antonyms.setSelected(true);


    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
