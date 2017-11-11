package com.example.acer.mindpicking;

import android.content.Context;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import util.FolderAdapter;
import util.NoteAdapter;

/**
 * Created by ACER on 2017/11/6.
 */

public class Folder{
    private String foldName=new String();
    private ArrayList<Note> note=new ArrayList<Note>();
    private NoteAdapter noteAdapter;
    public void setFoldName(String foldName){
        this.foldName=foldName;
    }
    public void addNote(Note note){
        this.note.add(note);
    }
    public String getFoldName(){
        return  foldName;
    }
    public ArrayList<Note> getNotes() {
        return note;
    }
    public void initAdapter(Context context){
        noteAdapter = new NoteAdapter(context,note);
    }
    public NoteAdapter getAdapter(){
        return noteAdapter;
    }
}
