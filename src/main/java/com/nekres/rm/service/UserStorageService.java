/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service;

import com.nekres.rm.pojo.UserStorage;
import org.springframework.stereotype.Service;

/**
 *
 * @author nekres
 */
@Service
public interface UserStorageService {
    public static final String ROOT = "root/";
    
    void save(UserStorage storage);
    
    void update(UserStorage storage);
    
    void delete(UserStorage storage);
    
    UserStorage findById(int id);
    
    boolean createStorage(String key);
    
    boolean mkdir(String directory, String key);
    
    boolean rename(String filepath, String oldName, String newName);
    
    
}
