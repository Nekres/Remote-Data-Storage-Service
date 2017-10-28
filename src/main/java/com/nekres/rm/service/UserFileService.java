/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service;

import com.nekres.rm.entity.UserFile;

/**
 *
 * @author nekres
 */
public interface UserFileService {
    
    void save(UserFile userFile);
    
    void update(UserFile userFile);
}
