package com.example.gom247.grandtoki.adapter;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gom247 on 16/03/2019.
 */

public class SharedPrefManager {

    public static final String SP_Grantoki_APP = "spGrandtokiApp";
    public static final String SP_Email = "spEmail";
    public static final String SP_Sudah_Login = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SP_Grantoki_APP, context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveInt(String keySP, int value) {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSP_Email(){
        return sp.getString(SP_Email, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_Sudah_Login, false);
    }
}
