package com.example.uyegirisliretrofit.Models;

public class KayitModeli {
    private boolean tf;
    private String text;

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return
                "KayitModeli{" +
                        "tf = '" + tf + '\'' +
                        ",text = '" + text + '\'' +
                        "}";
    }
}
