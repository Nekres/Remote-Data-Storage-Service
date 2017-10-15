/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.pojo.response;

/**
 *
 * @author nekres
 */
public class Response<T> {
    private String status;
    private T key;

    public Response(T key, String status) {
        this.key = key;
        this.status = status;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
