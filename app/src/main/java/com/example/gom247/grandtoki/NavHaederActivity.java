package com.example.gom247.grandtoki;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavHaederActivity extends AppCompatActivity {

    @BindView(R.id.txtEmail)
    TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_haeder);

        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(NavHaederActivity.this);
        String email = sharedPreferences.getString(MainActivity.Key_Email, null);

        txtEmail.setText(email);
    }
}
