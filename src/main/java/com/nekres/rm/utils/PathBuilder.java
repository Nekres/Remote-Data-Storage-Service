/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.utils;

/**
 *
 * @author nekres
 */
public class PathBuilder {
    
    private StringBuilder path = new StringBuilder();

    public PathBuilder() {
    }
    
    public PathBuilder appendDirectory(String directory) {
        if(directory == null || directory.isEmpty())
            return this;
        if (!path.toString().isEmpty() && path.charAt(path.length() - 1) != '/') {
            path.append('/');
        }
        path.append(directory);
        return this;
    }

    @Override
    public String toString() {
        return path.toString();
    }
    

    
}
