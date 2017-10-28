/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service;

import com.nekres.rm.entity.AccessRights;
import org.springframework.stereotype.Service;

/**
 *
 * @author nekres
 */
@Service
public interface AccessRightsService {
    void save(AccessRights accessRights);
    void update(AccessRights accessRights);
}
