package com.example.gom247.grandtoki;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gom247.grandtoki.adapter.ResponAdapter;
import com.example.gom247.grandtoki.api.BaseApiServer;
import com.example.gom247.grandtoki.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateBarangActivity extends AppCompatActivity {

    @BindView(R.id.edKodeBarang)
    EditText edKodeBarang;
    @BindView(R.id.edNamaBarang)
    EditText edNamaBarang;
    @BindView(R.id.edJenisBarang)
    EditText edJenisBarang;
    @BindView(R.id.edProdukBarang)
    EditText edProdukBarang;
    @BindView(R.id.edHargaBarang)
    EditText edHargaBarang;
    @BindView(R.id.btSimpan)
    Button btSimpan;

    BaseApiServer apiServer;
    Context context;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_barang);

        ButterKnife.bind(this);
        context = this;
        apiServer = UtilsApi.getApiService();
        getSupportActionBar().setTitle("Update Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String kode_barang = intent.getStringExtra("kode_barang");
        String nama_barang = intent.getStringExtra("nama_barang");
        String jenis_barang = intent.getStringExtra("jenis_barang");
        String produk_barang = intent.getStringExtra("produk_barang");
        String harga_barang = intent.getStringExtra("harga_barang");

        edKodeBarang.setText(kode_barang);
        edNamaBarang.setText(nama_barang);
        edJenisBarang.setText(jenis_barang);
        edProdukBarang.setText(produk_barang);
        edHargaBarang.setText(harga_barang);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProsesUpdateBarang();
            }
        });
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

    private void ProsesUpdateBarang() {

        String kode_barang = edKodeBarang.getText().toString();
        String nama_barang = edNamaBarang.getText().toString();
        String jenis_barang = edJenisBarang.getText().toString();
        String produk_barang = edProdukBarang.getText().toString();
        String harga_barang = edHargaBarang.getText().toString();

        progress = ProgressDialog.show(context, null, "Loading...", false, true);

        apiServer.UpdateBarang(kode_barang, nama_barang, jenis_barang, produk_barang, harga_barang).enqueue(new Callback<ResponAdapter>() {
            @Override
            public void onResponse(Call<ResponAdapter> call, Response<ResponAdapter> response) {

                String error = response.body().getError();
                String message = response.body().getMessage();
                progress.dismiss();

                if (error.equals("false")) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else if (error.equals("true")) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponAdapter> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(context, "Koneksi Loss", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
