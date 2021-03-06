package com.example.gom247.grandtoki;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.view.MenuItemCompat.getActionView;

public class DashBoardActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ButterKnife.bind(this);
        context = this;

        navigation.setOnNavigationItemSelectedListener(OnNavigation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.ngHome:
                   startActivity(new Intent(context, MainActivity.class));
                    return true;
                case R.id.ngKategori:
                    startActivity(new Intent(context, KategoriActivity.class));
                    return true;
                case R.id.ngAccount:
                    startActivity(new Intent(context, AccountActivity.class));
                    return true;
            }

            return false;
        }
    };




}
