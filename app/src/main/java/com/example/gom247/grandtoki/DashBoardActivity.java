package com.example.gom247.grandtoki;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    Context context;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationSelector = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ngHome:
                    startActivity(new Intent(context, MainActivity.class));
                    return true;
                case R.id.ngKategori:
                    startActivity(new Intent(context, MainActivity.class));
                    return true;
                case R.id.ngAccount:
                    startActivity(new Intent(context, Account_2Activity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ButterKnife.bind(this);
        context = this;

        navigation.setOnNavigationItemSelectedListener(navigationSelector);
    }
}
