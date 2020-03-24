package com.example.uyegirisliretrofit.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uyegirisliretrofit.R;
import com.example.uyegirisliretrofit.Utils.GetSharedPreferences;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity implements OSSubscriptionObserver {

    TextView textname;
    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tanimla();
        Kontrol();
        OneSignal.addSubscriptionObserver(this);
    }

    private void Tanimla() {
        preferences = new GetSharedPreferences(MainActivity.this);
        getSharedPreferences = preferences.getSession();
        textname = findViewById(R.id.textname);
        textname.setText(getSharedPreferences.getString("name", null));

    }

    public void Kontrol() {
        if (getSharedPreferences.getString("id", null) == null) {
            Intent intent = new Intent(getApplicationContext(), GirisYapActivity.class);
            startActivity(intent);
            finish();
        } else {

        }
    }

    public void CikisYap(View view) {
        preferences.deleteSessions();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Çıkış Yapıldı", Toast.LENGTH_LONG).show();
    }

    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (!stateChanges.getFrom().getSubscribed() &&
                stateChanges.getTo().getSubscribed()) {
            // The user is subscribed
            // Either the user subscribed for the first time
            // Or the user was subscribed -> unsubscribed -> subscribed
            stateChanges.getTo().getUserId();
            // Make a POST call to your server with the user ID
            Toast.makeText(getApplicationContext(), "Onesignal halloldu" + stateChanges.getTo().getUserId(), Toast.LENGTH_LONG).show();
            System.out.println("TAHA BEY ID: " + stateChanges.getTo().getUserId());
        }
    }
}
