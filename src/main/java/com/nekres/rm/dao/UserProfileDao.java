/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao;

import com.nekres.rm.pojo.UserProfile;
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
}
