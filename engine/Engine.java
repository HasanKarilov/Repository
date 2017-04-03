package com.company.engine;

/**
 * Created by Unix on 3/25/17.
 */
public class Engine {
    private String title;
    private String url;

    public Engine() {
        title = "";
        url = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
