package com.example.acer.mindpicking;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by ACER on 2017/11/5.
 */

public class Note extends DataSupport {
    public String getNoteName() {
        return noteName;
    }

    public String getImage() {
        return image;
    }



    public String getContent() {
        return content;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public void setContent(String content) {
        this.content = content;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    private String noteName;
    private String image;

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    private Folder folder;

    private String content;
    private String saveTime;
    private String feeling;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
