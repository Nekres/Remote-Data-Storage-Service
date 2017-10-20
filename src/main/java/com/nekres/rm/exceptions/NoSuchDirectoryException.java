/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.exceptions;

/**
 *
 * @author nekres
 */
public class NoSuchDirectoryException extends RemoteDataStoreServiceException{

    private String message;
    
    @Override
    public String getMessage() {
        return this.message;
    }

    public NoSuchDirectoryException() {
    }

    public NoSuchDirectoryException(String message) {
        this.message = message;
    }
    
    
    
}
