package com.example.gom247.grandtoki;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class BarangActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_barang);

        ButterKnife.bind(this);
        apiServer = UtilsApi.getApiService();
        context = this;
        getSupportActionBar().setTitle("Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProsesInputBarang();
            }
        });
    }

    private void ProsesInputBarang() {
        String kode_barang = edKodeBarang.getText().toString();
        String nama_barang = edNamaBarang.getText().toString();
        String jenis_barang = edJenisBarang.getText().toString();
        final String produk_barang = edProdukBarang.getText().toString();
        String harga_barang = edHargaBarang.getText().toString();

        progress = ProgressDialog.show(context, null, "Loading...", false, true);

        apiServer.InputBarang(kode_barang, nama_barang,jenis_barang,produk_barang, harga_barang).enqueue(new Callback<ResponAdapter>() {
            @Override
            public void onResponse(Call<ResponAdapter> call, Response<ResponAdapter> response) {

                String error = response.body().getError();
                String message = response.body().getMessage();

                if (error.equals("false")){
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else if (error.equals("true")){
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    edKodeBarang.setText("");
                    edNamaBarang.setText("");
                    edJenisBarang.setText("");
                    edProdukBarang.setText("");
                    edHargaBarang.setText("");
                }
            }

            @Override
            public void onFailure(Call<ResponAdapter> call, Throwable t) {
                Toast.makeText(context, "Connection Loss", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
