package com.example.proyectofinal.Model;

import java.util.List;

public class Reviews {

    /**
     * author : SWITCH.
     * content : ‘Ad Astra’ is about as art house as Hollywood cinema gets; disguising a metaphysical drama as an action-packed sci-fi adventure is a clever move for James Gray. While not perfect, it’s consistently entertaining whilst offering an introspective investigation on how parents influence their children. While a journey to the outer realms of our solar system, ‘Ad Astra’ is also an exploration of the human heart.
     - Charlie David Page

     Read Charlie's full article...
     https://www.maketheswitch.com.au/article/review-ad-astra-a-luscious-and-meticulous-space-drama
     * id : 5d7f61efc54304001096a5ef
     * url : https://www.themoviedb.org/review/5d7f61efc54304001096a5ef
     */

    private String author;
    private String content;
    private String id;
    private String url;

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
