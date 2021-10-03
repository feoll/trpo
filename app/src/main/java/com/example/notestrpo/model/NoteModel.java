package com.example.notestrpo.model;

import java.io.Serializable;

public class NoteModel implements Serializable {
    private long id;
    private String title;
    private String subtitle;
    private String description;
    private String time;
    private String uri = "empty";
    private int color;



    public NoteModel(String title, String subtitle, String description, String time, String uri, int color) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.time = time;
        this.uri = uri;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
