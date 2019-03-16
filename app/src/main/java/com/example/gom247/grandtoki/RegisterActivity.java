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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.edPhoto)
    EditText edPhoto;
    @BindView(R.id.edNama)
    EditText edNama;
    @BindView(R.id.edAlamat)
    EditText edAlamat;
    @BindView(R.id.edNotlp)
    EditText edNotlp;
    @BindView(R.id.btRegister)
    Button btRegister;

    BaseApiServer apiServer;
    Context context;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        context = this;
        apiServer = UtilsApi.getApiService();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgresRegister();
            }
        });
    }

    private void ProgresRegister() {

        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        String photo = edPhoto.getText().toString();
        String nama = edNama.getText().toString();
        String alamat = edAlamat.getText().toString();
        String notlp = edNotlp.getText().toString();

        progress = ProgressDialog.show(context, null, "loading...", false, true);

        apiServer.SignUp(email, password, photo, nama, alamat, notlp).enqueue(new Callback<ResponAdapter>() {
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
                    edEmail.setText("");
                    edPassword.setText("");
                    edPhoto.setText("");
                    edNama.setText("");
                    edAlamat.setText("");
                    edNotlp.setText("");
                }
            }

            @Override
            public void onFailure(Call<ResponAdapter> call, Throwable t) {

            }
        });
    }
}
