package com.example.acer.mindpicking;

/**
 * Created by ACER on 2017/11/5.
 */

public class Note {
    private String noteName;
    private int image;
    private String folder;
    private String content;
    private String saveTime;
    private String feeling;

    public void setImage(int image) {
        this.image = image;
    }
    public int getImage() {
        return image;
    }
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    public String getNoteName() {
        return noteName;
    }
    public void setFolder(String folder){this.folder=folder;}
    public String getFolder(){return this.folder;}
    public void setContent(String content){this.content=content;}
    public void setSaveTime(String saveTime){this.saveTime=saveTime;}
    public String getSaveTime(){return this.saveTime;}
    public void setFeeling(String feeling){this.feeling=feeling;}
    public String getFeeling(){return this.feeling;}
}
