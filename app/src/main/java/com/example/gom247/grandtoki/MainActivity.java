package com.example.gom247.grandtoki;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gom247.grandtoki.adapter.ResponAdapter;
import com.example.gom247.grandtoki.adapter.SharedPrefManager;
import com.example.gom247.grandtoki.api.BaseApiServer;
import com.example.gom247.grandtoki.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btRegister)
    Button btRegister;

    BaseApiServer apiServer;
    Context context;
    ProgressDialog progress;
    SharedPrefManager sharedPrefManager;

    public static final String Key_Email = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        context = this;
        apiServer = UtilsApi.getApiService();
        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(MainActivity.this, DashBoardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProsesLogin();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProsesRegister();
            }
        });

    }

    private void ProsesRegister() {
        startActivity(new Intent(context, SignUpActivity.class));
    }

    private void ProsesLogin() {

        final String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();

        progress = ProgressDialog.show(context, null, "Loading..", false, true);

        apiServer.Login(email, password).enqueue(new Callback<ResponAdapter>() {
            @Override
            public void onResponse(Call<ResponAdapter> call, Response<ResponAdapter> response) {

                String error = response.body().getError();
                String message = response.body().getMessage();
                progress.dismiss();

                if (error.equals("false")) {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Key_Email, email);
                    editor.apply();

                    sharedPrefManager.saveBoolean(SharedPrefManager.SP_Sudah_Login, true);

                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, DashBoardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();

                } else if (error.equals("true")) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    edPassword.setText("");
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
