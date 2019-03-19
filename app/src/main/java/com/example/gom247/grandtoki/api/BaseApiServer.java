package com.example.gom247.grandtoki.api;

import com.example.gom247.grandtoki.adapter.ResponAdapter;
import com.example.gom247.grandtoki.adapter.UserAdapter;

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

    @FormUrlEncoded
    @POST("regristrasi_user.php")
    Call<ResponAdapter> SignUp(@Field("email") String email,
                              @Field("password") String password,
                               @Field("photo") String photo,
                               @Field("nama") String nama,
                               @Field("alamat") String alamat,
                               @Field("notlp") String notlp);

    @FormUrlEncoded
    @POST("lihat_user.php")
    Call<UserAdapter> LihatUser(@Field("email") String email);

    @FormUrlEncoded
    @POST("edit_user.php")
    Call<ResponAdapter> UpdateUser(@Field("email") String email,
                                   @Field("photo") String photo,
                                   @Field("nama") String nama,
                                   @Field("alamat") String alamat,
                                   @Field("notlp") String notlp);

}
