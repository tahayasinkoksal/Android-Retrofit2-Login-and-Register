package com.example.uyegirisliretrofit.RestApi;

import com.example.uyegirisliretrofit.Models.GirisYapModel;
import com.example.uyegirisliretrofit.Models.KayitModeli;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<KayitModeli> kayitOl(String mail, String kullaniciAdi, String adSoyad, String parola, String userid) {
        Call<KayitModeli> x = getRestApi().kullaniciKaydet(BaseUrl.SECURENUMBER, mail, kullaniciAdi, adSoyad, parola, userid);
        return x;
    }

    public Call<GirisYapModel> UyeGirisi(String mail, String parola) {
        Call<GirisYapModel> x = getRestApi().girisYap(mail, parola);
        return x;
    }


}
