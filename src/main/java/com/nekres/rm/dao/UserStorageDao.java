/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao;

import com.nekres.rm.pojo.UserStorage;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nekres
 */
@Repository
public interface UserStorageDao {
    
    void save(UserStorage storage);
    
    void update(UserStorage storage);
    
    void delete(UserStorage storage);
    UserStorage getById(int id); 
    
}
