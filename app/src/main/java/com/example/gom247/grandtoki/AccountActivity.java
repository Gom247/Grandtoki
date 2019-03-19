package com.example.gom247.grandtoki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.gom247.grandtoki.adapter.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation_account)
    NavigationView nav_account;
    @BindView(R.id.txtEmail)
    TextView txtEmail;

    Context context;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);
        context = this;
        sharedPrefManager = new SharedPrefManager(this);
        nav_account.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AccountActivity.this);
        String email = sharedPreferences.getString(MainActivity.Key_Email, null);

        txtEmail.setText(email);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.ngAccount:
                startActivity(new Intent(context, LihatUserActivity.class));
                return true;

            case R.id.ngBarang:
                startActivity(new Intent(context, BarangActivity.class));
                return true;

            case R.id.ngLogout:

                sharedPrefManager.saveBoolean(SharedPrefManager.SP_Sudah_Login, false);
                startActivity(new Intent(AccountActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();

                return true;
        }

        return true;
    }
}
