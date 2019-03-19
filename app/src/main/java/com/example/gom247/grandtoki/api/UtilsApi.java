package com.example.gom247.grandtoki.api;

/**
 * Created by Gom247 on 14/03/2019.
 */

public class UtilsApi {

    public static final String Base_Url = "http://192.168.1.105/Grandtoki/";

    public static BaseApiServer getApiService(){
        return RetrofitClient.getClient(Base_Url).create(BaseApiServer.class);
    }
}
