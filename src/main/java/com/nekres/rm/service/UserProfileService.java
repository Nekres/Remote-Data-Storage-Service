/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service;

import com.nekres.rm.entity.UserProfile;
import com.nekres.rm.entity.UserStorage;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author nekres
 */
@Service
public interface UserProfileService {
    
    void save(UserProfile profile);
    
    public boolean isKeyExist(String key);
    
    public boolean isLoginBusy(String login);
    
    public UserProfile get(String login, String password);
    
    public int getStorageByKey(String key);
}
