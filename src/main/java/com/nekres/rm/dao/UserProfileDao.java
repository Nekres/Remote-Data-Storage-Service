/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao;

import com.nekres.rm.entity.UserProfile;
import com.nekres.rm.entity.UserStorage;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nekres
 */
@Repository
public interface UserProfileDao {
    
    void save(UserProfile profile);
    boolean isKeyExist(String key);
    boolean isLoginBusy(String login);
    UserProfile get(String login, String password);
    public int getStorageByKey(String key);
}
