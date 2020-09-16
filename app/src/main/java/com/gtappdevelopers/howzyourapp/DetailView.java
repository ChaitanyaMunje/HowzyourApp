package com.gtappdevelopers.howzyourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailView extends AppCompatActivity {

    String name,login,desc;
    EditText name_edt,login_edt,desc_edt;
    Button update_btn;
    RetroAPI retroAPI;
    String BASE_URL="https://api.github.com/search/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        name=getIntent().getStringExtra("name");
        login=getIntent().getStringExtra("login");
        desc=getIntent().getStringExtra("desc");
        update_btn=findViewById(R.id.update_btn);


        name_edt=findViewById(R.id.edt_full_name);
        login_edt=findViewById(R.id.edt_login);
        desc_edt=findViewById(R.id.edt_desc);

        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retroAPI = retrofit.create(RetroAPI.class);



        name_edt.setText(name);
        login_edt.setText(login);
        desc_edt.setText(desc);


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=name_edt.getText().toString();
                String login=login_edt.getText().toString();
                String desc=desc_edt.getText().toString();

                //updatedata(name,login,desc);


                Toast.makeText(DetailView.this, "Unable to Update Data..", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void updatedata(String name, String login, String desc){

        ArrayList<Items>itemsArrayList=new ArrayList<>();
        Owner owner=new Owner(login);
        itemsArrayList.add(new Items(name,owner,desc));

        JsonFeed feed=new JsonFeed(itemsArrayList);

        String finlurl=BASE_URL+"repositories?q=language:"+"java";
        Call<JsonFeed> call = retroAPI.patchData(feed);

        call.enqueue(new Callback<JsonFeed>() {
            @Override
            public void onResponse(Call<JsonFeed> call, Response<JsonFeed> response) {
                    Log.e("UPDATETAG","UPDATE RESPONSE = "+response.code());
                    ArrayList<Items> itemsArrayList=response.body().getItemsArrayList();
                    for (int i=0; i<itemsArrayList.size(); i++){

                        Log.e("UPDATETAG","Item DATA = "+itemsArrayList.get(i).getDescription()+"\n\n"+itemsArrayList.get(i).getFull_name()
                                +"\n\n"+itemsArrayList.get(i).getOwner().getLogin()
                                +"\n\n\n\n\n");




                }

            }

            @Override
            public void onFailure(Call<JsonFeed> call, Throwable t) {
                Log.e("UPDATETAG","RES TEXT = "+t.getMessage());


            }
        });


    }

}