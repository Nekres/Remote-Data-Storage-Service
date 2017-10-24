/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.pojo.response;

import java.util.ArrayList;

/**
 *
 * @author nekres
 */
public class Tuple {
    
    private ArrayList<String> directories;
    private ArrayList<String> files;

    public Tuple(ArrayList<String> directories, ArrayList<String> files) {
        this.directories = directories;
        this.files = files;
    }
    
    public ArrayList<String> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<String> directories) {
        this.directories = directories;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }
    
    
}
