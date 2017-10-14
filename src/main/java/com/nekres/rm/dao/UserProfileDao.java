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
    
    void add(UserProfile profile);
    
}
