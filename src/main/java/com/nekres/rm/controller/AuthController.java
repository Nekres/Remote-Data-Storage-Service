/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.controller;

import com.nekres.rm.entity.UserStorage;
import com.nekres.rm.entity.UserProfile;
import com.nekres.rm.exceptions.NoSuchStorageException;
import com.nekres.rm.pojo.response.Response;
import com.nekres.rm.service.*;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author nekres
 */
@Controller
@RequestMapping("/")
public class AuthController {
    private Logger logger = Logger.getLogger(AuthController.class.getName());
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserStorageService storageService;
    
    @RequestMapping(path = "/auth", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response<String>> createStorage(@RequestParam final String login, @RequestParam final String password) throws IOException{
        if(userProfileService.isLoginBusy(login)){
            ResponseEntity response = new ResponseEntity(new Response<String>("login is busy","error"),HttpStatus.UNAUTHORIZED);
            return response;
        }
        String key = UUID.randomUUID().toString();
        
        while(userProfileService.isKeyExist(key)){ //simple way to prevent duplicates
            key = UUID.randomUUID().toString();
        }
        UserStorage storage = new UserStorage();
        storage.setMountPoint(key);
        
        UserProfile user = new UserProfile();
        user.setEmail(login);
        user.setPassword(password);
        user.setStorageKey(key);
        user.setStorage(storage);
        
        storageService.createStorage(key);
        storageService.save(storage);
        userProfileService.save(user);
        
        ResponseEntity<Response<String>> response = new ResponseEntity<>(new Response<String>(user.getStorageKey(),"success"),HttpStatus.OK);
        return response;
    }
    @RequestMapping(path = "/receiveKey", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response<String>> receiveKey(@RequestParam final String login, final String password){
        UserProfile user = userProfileService.get(login, password);
        return new ResponseEntity<Response<String>>(new Response<String>(user.getStorageKey(), "success"),HttpStatus.OK);
    }
    
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleException(NoSuchStorageException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<String>("Storage does not exist", "error"));
    }
    
}
