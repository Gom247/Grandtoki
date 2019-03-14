package com.example.gom247.grandtoki.api;

import com.example.gom247.grandtoki.adapter.ResponAdapter;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Gom247 on 14/03/2019.
 */

public interface BaseApiServer {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponAdapter> Login(@Field("email") String email,
                               @Field("password") String password);

}
