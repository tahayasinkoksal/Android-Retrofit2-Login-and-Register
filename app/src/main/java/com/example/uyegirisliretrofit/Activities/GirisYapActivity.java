package com.example.uyegirisliretrofit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uyegirisliretrofit.Models.GirisYapModel;
import com.example.uyegirisliretrofit.R;
import com.example.uyegirisliretrofit.RestApi.ManagerAll;
import com.example.uyegirisliretrofit.Utils.GetSharedPreferences;
import com.example.uyegirisliretrofit.Utils.Warnings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GirisYapActivity extends AppCompatActivity {

    EditText mailEditText, parolaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        Tanimlama();
    }


    public void Tanimlama() {
        mailEditText = findViewById(R.id.mailEditText);
        parolaEditText = findViewById(R.id.parolaEditText);
    }

    public void Temizle() {
        mailEditText.setText("");
        parolaEditText.setText("");
    }

    public void uyeOlEkraniGit(View view) {
        Intent intent = new Intent(getApplicationContext(), KayitOlActivitiy.class);
        startActivity(intent);
        Temizle();
    }

    public void GirisYapButton(View view) {
        String mail = mailEditText.getText().toString();
        String parola = parolaEditText.getText().toString();

        UyeGirisi(mail, parola);
        Temizle();
    }


    public void UyeGirisi(String mail, String parola) {
        Call<GirisYapModel> reg = ManagerAll.getInstance().UyeGirisi(mail, parola);

        reg.enqueue(new Callback<GirisYapModel>() {
            @Override
            public void onResponse(Call<GirisYapModel> call, Response<GirisYapModel> response) {
                if (response.body().isTf()) {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    Temizle();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    GetSharedPreferences getSharedPreferences = new GetSharedPreferences(GirisYapActivity.this);
                    getSharedPreferences.setSession(
                            response.body().getId().toString(),
                            response.body().getKullaniciadi().toString(),
                            response.body().getMail().toString(),
                            response.body().getAdsoyad().toString());

                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    Temizle();
                }
            }

            @Override
            public void onFailure(Call<GirisYapModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
                Temizle();
            }
        });
    }
}
