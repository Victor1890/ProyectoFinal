package com.example.proyectofinal.Model;

import java.io.Serializable;

public class Reviews implements Serializable {

    private String author;
    private String content;
    private String id;
    private String url;

    private static Reviews instance = null;

    public static Reviews getInstance() {
        if(instance == null){
            instance = new Reviews();
        }
        return instance;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
