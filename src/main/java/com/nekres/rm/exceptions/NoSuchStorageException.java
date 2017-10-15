/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author nekres
 */
public class NoSuchStorageException extends RemoteDataStoreServiceException{
    
    private String message;

    public NoSuchStorageException(String message) {
        this.message = message;
    }

    public NoSuchStorageException() {
    }

    @Override
    public String getMessage() {
        return this.message; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
