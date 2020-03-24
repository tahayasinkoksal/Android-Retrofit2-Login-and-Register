package com.example.uyegirisliretrofit.RestApi;

import com.example.uyegirisliretrofit.Models.GirisYapModel;
import com.example.uyegirisliretrofit.Models.KayitModeli;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {


    @FormUrlEncoded
    @POST("/uygulamaservisi/kayitol.php")
    Call<KayitModeli> kullaniciKaydet(@Field("secure") String guvenlikAnahtarı, @Field("mail") String mailadres, @Field("kadi") String kadi, @Field("adsoyad") String adsoyad, @Field("parola") String parola, @Field("userid") String userid);


    @FormUrlEncoded
    @POST("/uygulamaservisi/girisyap.php")
    Call<GirisYapModel> girisYap(@Field("mail") String mailadres, @Field("parola") String parola);

}
