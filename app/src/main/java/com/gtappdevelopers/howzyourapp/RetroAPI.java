package com.gtappdevelopers.howzyourapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetroAPI {



    @Headers("Content-Type: application/json")

    @GET
    Call<JsonFeed>getData(@Url String url);

    @FormUrlEncoded
    @PUT
    Call<JsonFeed>updateData(@Url String url);


    @PATCH("")
    Call<JsonFeed> patchData(@Body JsonFeed feed);



}
