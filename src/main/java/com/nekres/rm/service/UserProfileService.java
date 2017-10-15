/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service;

import com.nekres.rm.pojo.UserProfile;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author nekres
 */
@Service
public interface UserProfileService {
    
    void save(UserProfile profile);
    
    public boolean isKeyBusy(String key);
    
    public boolean isLoginBusy(String login);
    
    public UserProfile get(String login, String password);
}
