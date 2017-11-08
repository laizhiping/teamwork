package com.example.acer.mindpicking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 2017/11/6.
 */

public class Folder {
    private String foldName;
    private ArrayList<Note> notes;
    public  Folder(){
        notes=new ArrayList<Note>();
        foldName=new String();
    }
    public void setFoldName(String foldName){
        this.foldName=foldName;
    }
    public void addNote(Note note){
        notes.add(note);
    }
    public String getFoldName(){
        return  foldName;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
