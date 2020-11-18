package com.example.mymovies.data;

public class Review {

    private String autho;
    private String content;

    public Review(String autho, String content) {
        this.autho = autho;
        this.content = content;
    }

    public String getAuthor() {
        return autho;
    }

    public void setAutho(String autho) {
        this.autho = autho;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
