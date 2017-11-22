package com.example.acer.mindpicking;

import android.content.Context;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import util.FolderAdapter;
import util.NoteAdapter;

/**
 * Created by ACER on 2017/11/6.
 */

public class Folder extends DataSupport{
    public String getFoldName() {
        return foldName;
    }

    public ArrayList<Note> getNote() {
        return note;
    }

    public NoteAdapter getNoteAdapter() {
        return noteAdapter;
    }

    public void setFoldName(String foldName) {
        this.foldName = foldName;
    }

    public void setNote(ArrayList<Note> note) {
        this.note = note;
    }

    public void setNoteAdapter(NoteAdapter noteAdapter) {
        this.noteAdapter = noteAdapter;
    }

    private String foldName;
    private ArrayList<Note> note=new ArrayList<Note>();
    private NoteAdapter noteAdapter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public void initAdapter(Context context){
        noteAdapter = new NoteAdapter(context,note);
    }

}
