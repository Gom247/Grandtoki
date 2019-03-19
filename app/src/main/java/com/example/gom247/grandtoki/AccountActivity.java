package com.example.gom247.grandtoki;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.gom247.grandtoki.adapter.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation_account)
    NavigationView nav_account;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);
        nav_account.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ngAccount){

        } else if (id == R.id.ngBarang) {

        } else if (id == R.id.ngLogout){
            sharedPrefManager.saveBoolean(SharedPrefManager.SP_Sudah_Login, false);
            startActivity(new Intent(AccountActivity.this, DashBoardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        return true;
    }
}