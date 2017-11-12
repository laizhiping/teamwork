package com.example.acer.mindpicking;

/**
 * Created by ACER on 2017/11/5.
 */

public class Note {
    private String noteName;
    private int image;
    public Note(){
        noteName=new String();
    }
    public void setImage(int image) {
        this.image = image;
    }
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public int getImage() {
        return image;
    }

    public String getNoteName() {
        return noteName;
    }
}
