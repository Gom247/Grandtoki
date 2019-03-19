package com.example.gom247.grandtoki;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gom247.grandtoki.adapter.ResponAdapter;
import com.example.gom247.grandtoki.adapter.UserAdapter;
import com.example.gom247.grandtoki.api.BaseApiServer;
import com.example.gom247.grandtoki.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatUserActivity extends AppCompatActivity {

    @BindView(R.id.txtEmail)
    TextView txtEmail;
    @BindView(R.id.txtPhoto)
    TextView txtPhoto;
    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtNotlp)
    TextView txtNotlp;
    @BindView(R.id.cvUser)
    CardView cvUser;
    
    BaseApiServer apiServer;
    ProgressDialog progress;
    Context context;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_user);

        ButterKnife.bind(this);
        context = this;
        apiServer = UtilsApi.getApiService();
        getSupportActionBar().setTitle("Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProsesUpdate();
            }
        });
        
        LoadDataUser();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadDataUser();
    }

    private void LoadDataUser() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LihatUserActivity.this);
        String email = sharedPreferences.getString(MainActivity.Key_Email, null);
        
        progress = ProgressDialog.show(context, null, "Loading...", false, true);
        
        apiServer.LihatUser(email).enqueue(new Callback<UserAdapter>() {
            @Override
            public void onResponse(Call<UserAdapter> call, Response<UserAdapter> response) {

                progress.dismiss();
                
                if (response.isSuccessful()) {
                    
                    txtEmail.setText(response.body().getEmail());
                    txtPhoto.setText(response.body().getPhoto());
                    txtNama.setText(response.body().getNama());
                    txtAlamat.setText(response.body().getAlamat());
                    txtNotlp.setText(response.body().getNotlp());
                    
                }
            }

            @Override
            public void onFailure(Call<UserAdapter> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(context, "Connection Loss", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ProsesUpdate() {

        String email = txtEmail.getText().toString();
        String photo = txtPhoto.getText().toString();
        String nama = txtNama.getText().toString();
        String alamat = txtAlamat.getText().toString();
        String notlp = txtNotlp.getText().toString();

        Intent i = new Intent(context, UpdateUserActivity.class);
        i.putExtra("email", email);
        i.putExtra("photo", photo);
        i.putExtra("nama", nama);
        i.putExtra("alamat", alamat);
        i.putExtra("notlp", notlp);
        context.startActivity(i);
        
    }
}
