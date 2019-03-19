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

public class UpdateUserActivity extends AppCompatActivity {

    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPhoto)
    EditText edPhoto;
    @BindView(R.id.edNama)
    EditText edNama;
    @BindView(R.id.edAlamat)
    EditText edAlamat;
    @BindView(R.id.edNotlp)
    EditText edNotlp;
    @BindView(R.id.btUpdate)
    Button btUpdate;

    BaseApiServer apiServer;
    Context context;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        ButterKnife.bind(this);
        context = this;
        apiServer = UtilsApi.getApiService();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Account");

        Intent i = getIntent();
        String email = i.getStringExtra("email");
        String photo = i.getStringExtra("photo");
        String nama = i.getStringExtra("nama");
        String alamat = i.getStringExtra("alamat");
        String notlp = i.getStringExtra("notlp");

        edEmail.setText(email);
        edPhoto.setText(photo);
        edNama.setText(nama);
        edAlamat.setText(alamat);
        edNotlp.setText(notlp);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProsesUpdate();
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

    private void ProsesUpdate() {

        String email = edEmail.getText().toString();
        String photo = edPhoto.getText().toString();
        String nama = edNama.getText().toString();
        String alamat = edAlamat.getText().toString();
        String notlp = edNotlp.getText().toString();

        progress = ProgressDialog.show(context, null, "loading...", false, true);

        apiServer.UpdateUser(email, photo, nama, alamat, notlp).enqueue(new Callback<ResponAdapter>() {
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
                Toast.makeText(context, "Connection Loss", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
