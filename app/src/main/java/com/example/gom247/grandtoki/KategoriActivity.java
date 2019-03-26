package com.example.gom247.grandtoki;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.gom247.grandtoki.adapter.KategoriAdapter;
import com.example.gom247.grandtoki.adapter.ResponAdapter;
import com.example.gom247.grandtoki.api.BaseApiServer;
import com.example.gom247.grandtoki.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity {

    @BindView(R.id.rvMenu)
    RecyclerView rvMenu;
    @BindView(R.id.pgLoading)
    ProgressBar pgLoading;

    BaseApiServer apiServer;
    Context context;
    List<KategoriAdapter> list = new ArrayList<>();
    KategoriViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        ButterKnife.bind(this);
        context = this;
        apiServer = UtilsApi.getApiService();
        getSupportActionBar().setTitle("Kategori");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewAdapter = new KategoriViewAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setItemAnimator(new DefaultItemAnimator());
        rvMenu.setAdapter(viewAdapter);

        LoadDataMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadDataMenu() {

        apiServer.Menu().enqueue(new Callback<ResponAdapter>() {
            @Override
            public void onResponse(Call<ResponAdapter> call, Response<ResponAdapter> response) {
                
            }

            @Override
            public void onFailure(Call<ResponAdapter> call, Throwable t) {

            }
        });

    }
}
