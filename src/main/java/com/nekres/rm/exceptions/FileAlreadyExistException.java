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
public class FileAlreadyExistException extends RemoteDataStoreServiceException{
    
    private String message;

    public FileAlreadyExistException(String message) {
        this.message = message;
    }

    public FileAlreadyExistException() {
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
    
}
