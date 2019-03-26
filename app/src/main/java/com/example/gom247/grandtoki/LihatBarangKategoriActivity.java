package com.example.gom247.grandtoki;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

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

public class LihatBarangKategoriActivity extends AppCompatActivity {

    @BindView(R.id.pgLoading)
    ProgressBar pgLoading;
    @BindView(R.id.rvBarang)
    RecyclerView rvBarang;

    BaseApiServer apiServer;
    Context context;
    List<BarangAdapter> data_produk = new ArrayList<>();
    BarangViewAdapter2 viewAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang_kategori);

        ButterKnife.bind(this);
        context = this;
        apiServer = UtilsApi.getApiService();
        getSupportActionBar().setTitle("Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewAdapter2 = new BarangViewAdapter2(this, data_produk);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvBarang.setLayoutManager(layoutManager);
        rvBarang.setItemAnimator(new DefaultItemAnimator());
        rvBarang.setAdapter(viewAdapter2);

        LoadDataProduk();
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

    private void LoadDataProduk() {

        Intent intent = getIntent();
        String jenis_barang = intent.getStringExtra("jenis_barang");

        apiServer.LihatJenisBarang(jenis_barang).enqueue(new Callback<ResponAdapter>() {
            @Override
            public void onResponse(Call<ResponAdapter> call, Response<ResponAdapter> response) {

                String error = response.body().getError();
                pgLoading.setVisibility(View.GONE);

                if (error.equals("1")) {
                    data_produk = response.body().getData_produk();
                    viewAdapter2 = new BarangViewAdapter2(context, data_produk);
                    rvBarang.setAdapter(viewAdapter2);
                }
            }

            @Override
            public void onFailure(Call<ResponAdapter> call, Throwable t) {

            }
        });
    }
}
