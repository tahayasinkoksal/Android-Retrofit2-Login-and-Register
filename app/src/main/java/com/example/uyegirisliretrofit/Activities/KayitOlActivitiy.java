package com.example.uyegirisliretrofit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uyegirisliretrofit.Models.KayitModeli;
import com.example.uyegirisliretrofit.R;
import com.example.uyegirisliretrofit.RestApi.ManagerAll;
import com.example.uyegirisliretrofit.Utils.OneSignalClass;
import com.example.uyegirisliretrofit.Utils.Warnings;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KayitOlActivitiy extends AppCompatActivity implements OSSubscriptionObserver {

    private EditText mailEditText, kullaniciadiEditText, adsoyadEditText, parolaEditText;
    private Button kayitButton;

    private String bidirimUserId;

    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (!stateChanges.getFrom().getSubscribed() &&
                stateChanges.getTo().getSubscribed()) {
            // The user is subscribed
            // Either the user subscribed for the first time
            // Or the user was subscribed -> unsubscribed -> subscribed
            stateChanges.getTo().getUserId();
            // Make a POST call to your server with the user ID

            bidirimUserId = stateChanges.getTo().getUserId();
            Toast.makeText(getApplicationContext(), "Onesignal halloldu" + stateChanges.getTo().getUserId(), Toast.LENGTH_LONG).show();
            System.out.println("TAHA BEY ID: " + stateChanges.getTo().getUserId());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol_activitiy);

        tanimla();
        kayitOl();
        OneSignal.addSubscriptionObserver(this);



    }

    private void tanimla() {
        kayitButton = findViewById(R.id.kayitButton);
        mailEditText = findViewById(R.id.mailEditText);
        kullaniciadiEditText = findViewById(R.id.kullaniciadiEditText);
        adsoyadEditText = findViewById(R.id.adsoyadEditText);
        parolaEditText = findViewById(R.id.parolaEditText);


    }

    private void Temizle() {
        mailEditText.setText("");
        kullaniciadiEditText.setText("");
        adsoyadEditText.setText("");
        parolaEditText.setText("");
    }


    public void kayitOl() {
        //Kayıt Butuna tıkladnığında olacklar

        kayitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail5 = mailEditText.getText().toString().trim();
                String kadi5 = kullaniciadiEditText.getText().toString().trim();
                String adsoyad5 = adsoyadEditText.getText().toString().trim();
                String parola5 = parolaEditText.getText().toString().trim();
                System.out.println("TYK" + mail5 + kadi5 + adsoyad5 + parola5);

                System.out.println("Bildirim Id: "+bidirimUserId);
                kayit(mail5, kadi5, adsoyad5, parola5, bidirimUserId);


                Temizle();

            }
        });


    }

    public void kayit(String mail, String kullaniciAdi, String adSoyad, String parola, String userId) {
        Call<KayitModeli> reg = ManagerAll.getInstance().kayitOl(mail, kullaniciAdi, adSoyad, parola, userId);

        reg.enqueue(new Callback<KayitModeli>() {
            @Override
            public void onResponse(Call<KayitModeli> call, Response<KayitModeli> response) {
                if (response.body().isTf()) {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    Temizle();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    Temizle();
                }
            }

            @Override
            public void onFailure(Call<KayitModeli> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
                Temizle();
            }
        });


    }

    public void uyeGirisiEkraniGit(View view) {
        Intent intent = new Intent(getApplicationContext(), GirisYapActivity.class);
        startActivity(intent);
        finish();
    }


}

