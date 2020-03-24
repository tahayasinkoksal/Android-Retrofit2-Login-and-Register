package com.example.uyegirisliretrofit.Utils;

import android.app.Activity;
import android.content.SharedPreferences;

public class GetSharedPreferences {
    private SharedPreferences sharedPreferences;
    private Activity activity;
    private String bildirim;

    public GetSharedPreferences(Activity activity) {
        this.activity = activity;
    }

    public SharedPreferences getSession() {
        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session", 0);
        return sharedPreferences;
    }

    public void setSession(String id, String username, String mail, String name) {
        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.putString("username", username);
        editor.putString("mail", mail);
        editor.putString("name", name);
        editor.commit();
    }


    public void deleteSessions() {

        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
