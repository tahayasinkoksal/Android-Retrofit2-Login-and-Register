package com.example.uyegirisliretrofit.Models;

public class GirisYapModel {
    private boolean tf;
    private Object mail;
    private Object adsoyad;
    private Object parola;
    private Object id;
    private String text;
    private Object kullaniciadi;

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public Object getMail() {
        return mail;
    }

    public void setMail(Object mail) {
        this.mail = mail;
    }

    public Object getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(Object adsoyad) {
        this.adsoyad = adsoyad;
    }

    public Object getParola() {
        return parola;
    }

    public void setParola(Object parola) {
        this.parola = parola;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(Object kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    @Override
    public String toString() {
        return
                "GirisYapModel{" +
                        "tf = '" + tf + '\'' +
                        ",mail = '" + mail + '\'' +
                        ",adsoyad = '" + adsoyad + '\'' +
                        ",parola = '" + parola + '\'' +
                        ",id = '" + id + '\'' +
                        ",text = '" + text + '\'' +
                        ",kullaniciadi = '" + kullaniciadi + '\'' +
                        "}";
    }
}
