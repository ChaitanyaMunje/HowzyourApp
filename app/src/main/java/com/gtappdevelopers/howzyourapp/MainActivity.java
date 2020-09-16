package com.gtappdevelopers.howzyourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageButton get_data;
    String BASE_URL = "https://api.github.com/search/";
    RecyclerView result_recycler;
    private EditText lang_edt;
    private RelativeLayout no_data_layout;
    private TextView load_txt;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_data = findViewById(R.id.get_data_btn);
        result_recycler = findViewById(R.id.result_recycler);
        lang_edt = findViewById(R.id.edt_language);
        no_data_layout = findViewById(R.id.load_layout);
        load_txt = findViewById(R.id.load_txt);
        progressBar = findViewById(R.id.progressbar);


        get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lang_edt.getText().toString().isEmpty()) {
                    lang_edt.setError("Please enter some language");
                } else {
                    fetchdata(lang_edt.getText().toString());
                }


            }
        });


    }

    private void fetchdata(String language) {

        no_data_layout.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroAPI retroapi = retrofit.create(RetroAPI.class);

        String finlurl = BASE_URL + "repositories?q=language:" + language;
        Call<JsonFeed> call = retroapi.getData(finlurl);

        call.enqueue(new Callback<JsonFeed>() {
            @Override
            public void onResponse(Call<JsonFeed> call, Response<JsonFeed> response) {

                Log.e("TAG", "JSON DATA  = " + response.body());
                Log.e("CODE", "RESPONSE CODE = " + response.toString());

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);
                    load_txt.setText("No Data Found");
                    return;
                }


                no_data_layout.setVisibility(View.GONE);


                ArrayList<Items> itemsArrayList = response.body().getItemsArrayList();

                for (int i = 0; i < itemsArrayList.size(); i++) {
                    Log.e("DATATAG", "Item DATA = " + itemsArrayList.get(i).getDescription() + "\n\n" + itemsArrayList.get(i).getFull_name()
                            + "\n\n" + itemsArrayList.get(i).getOwner().getLogin()
                            + "\n\n\n\n\n");

                    RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this, itemsArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                    result_recycler.setLayoutManager(linearLayoutManager);
                    result_recycler.setAdapter(adapter);


                }


            }

            @Override
            public void onFailure(Call<JsonFeed> call, Throwable t) {

                Log.e("TAG", "ERRORR = " + t.getMessage());


            }
        });


    }


}