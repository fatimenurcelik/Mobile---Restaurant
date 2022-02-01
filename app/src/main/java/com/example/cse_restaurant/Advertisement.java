package com.example.cse_restaurant;

public class Advertisement {

    String title;
    String resim;
    String explanation;

    public Advertisement(String title, String resim, String explanation) {
        this.title = title;
        this.resim = resim;
        this.explanation = explanation;
    }

    public Advertisement() {
    }

    public Advertisement(String image) {
        this.resim = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
