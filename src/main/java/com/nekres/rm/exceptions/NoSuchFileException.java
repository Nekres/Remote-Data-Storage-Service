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
public class NoSuchFileException extends RemoteDataStoreServiceException{
    
    private String message;

    public NoSuchFileException(String message) {
        this.message = message;
    }

    public NoSuchFileException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }
    
    
}
