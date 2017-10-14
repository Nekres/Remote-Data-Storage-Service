/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.controller;

import com.nekres.rm.pojo.UserProfile;
import com.nekres.rm.service.UserProfileService;
import com.nekres.rm.service.UserStorageService;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UUID;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author nekres
 */
@Controller
@RequestMapping("/")
public class StorageController {
    private Logger logger = Logger.getLogger(StorageController.class.getName());
    @Autowired
    private UserProfileService userProfileService;
    
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    @ResponseBody
    public String createStorage(@RequestParam("email") String email, @RequestParam String password){
        UserProfile user = new UserProfile();
        String key = UUID.randomUUID().toString();
        while(userProfileService.isKeyBusy(key)){
            key = UUID.randomUUID().toString();
        }
        user.setEmail(email);
        user.setPassword(password);
        user.setStorageId("1");
        user.setStorageKey(key);
        userProfileService.save(user);
        return user.getStorageKey();
    }
}
