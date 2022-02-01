package com.example.cse_restaurant;

public class Food {
    String name;
    String fiyat;
    String resim;
    String acıklama;

    public Food(String name, String fiyat, String resim, String acıklama) {
        this.name = name;
        this.fiyat = fiyat;
        this.resim = resim;
        this.acıklama = acıklama;
    }

    public Food() {
    }

    public Food(String image) {
        this.resim = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFiyat() {

        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getAcıklama() {
        return acıklama;
    }

    public void setAcıklama(String acıklama) {
        this.acıklama = acıklama;
    }

}
