package com.example.gom247.grandtoki;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.gom247.grandtoki.adapter.BarangAdapter;
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

public class LihatBarangActivity extends AppCompatActivity {

    @BindView(R.id.pgLoading)
    ProgressBar pgLoading;
    @BindView(R.id.rvBarang)
    RecyclerView rvBarang;

    BaseApiServer apiServer;
    List<BarangAdapter> respone_barang = new ArrayList<>();
    BarangViewAdapter viewAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        ButterKnife.bind(this);
        apiServer = UtilsApi.getApiService();
        context = this;
        getSupportActionBar().setTitle("List Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewAdapter = new BarangViewAdapter(this, respone_barang);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvBarang.setLayoutManager(layoutManager);
        rvBarang.setItemAnimator(new DefaultItemAnimator());
        rvBarang.setAdapter(viewAdapter);

        LoadDataBarang();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadDataBarang();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadDataBarang() {

        apiServer.LihatBarang().enqueue(new Callback<ResponAdapter>() {
            @Override
            public void onResponse(Call<ResponAdapter> call, Response<ResponAdapter> response) {

                String error = response.body().getError();
                pgLoading.setVisibility(View.GONE);

                if (error.equals("1")) {
                    respone_barang = response.body().getRespone_barang();
                    viewAdapter = new BarangViewAdapter(context, respone_barang);
                    rvBarang.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponAdapter> call, Throwable t) {
                pgLoading.setVisibility(View.GONE);
                Toast.makeText(context, "Koneksi Loss", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.ngSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Cari Barang");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                rvBarang.setVisibility(View.GONE);
                pgLoading.setVisibility(View.VISIBLE);

                apiServer.Search(newText).enqueue(new Callback<ResponAdapter>() {
                    @Override
                    public void onResponse(Call<ResponAdapter> call, Response<ResponAdapter> response) {

                        String error = response.body().getError();
                        pgLoading.setVisibility(View.GONE);
                        rvBarang.setVisibility(View.VISIBLE);

                        if (error.equals("1")) {

                            respone_barang = response.body().getRespone_barang();
                            viewAdapter = new BarangViewAdapter(context, respone_barang);
                            rvBarang.setAdapter(viewAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponAdapter> call, Throwable t) {

                    }
                });
                return true;
            }
        });

        return true;
    }
}
