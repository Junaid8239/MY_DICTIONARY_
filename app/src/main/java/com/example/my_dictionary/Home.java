package com.example.my_dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import android.widget.TextView;
import android.widget.Toast;

import com.example.my_dictionary.Adapters.MeaningAdapter;
import com.example.my_dictionary.Adapters.PhoneticsAdapter;
import com.example.my_dictionary.Models.APIResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Home extends AppCompatActivity {
    SearchView search_view;
    TextView textView_word;
    RecyclerView recycler_phonetics,recycler_meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        recycler_meanings = findViewById(R.id.recycler_meanings);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading..");
        progressDialog.show();
        RequestManager manager =  new RequestManager(this);
        manager.getWordMeaning(listener,"search");


        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for "+query);
                progressDialog.show();
                RequestManager manager =  new RequestManager(Home.this);
                manager.getWordMeaning(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        }


    public final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse==null){
                Toast.makeText(Home.this, "No data found!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }


        public void showData(APIResponse apiResponse) {

            textView_word.setText("Word:" + apiResponse.getWord());
            recycler_phonetics.setHasFixedSize(true);
            recycler_phonetics.setLayoutManager(new GridLayoutManager(Home.this,1));
            phoneticsAdapter =new  PhoneticsAdapter(Home.this ,apiResponse.getPhonetics());
            recycler_phonetics.setAdapter(phoneticsAdapter);

            recycler_meanings.setHasFixedSize(true);
            recycler_meanings.setLayoutManager(new GridLayoutManager(Home.this,1));
            meaningAdapter = new MeaningAdapter(Home.this,apiResponse.getMeanings());
            recycler_meanings.setAdapter(meaningAdapter);


        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();

        }
    };
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}



