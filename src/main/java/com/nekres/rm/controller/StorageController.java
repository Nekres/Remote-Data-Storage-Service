/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.controller;

import com.nekres.rm.exceptions.NoSuchFileException;
import com.nekres.rm.exceptions.NoSuchStorageException;
import com.nekres.rm.pojo.response.Response;
import com.nekres.rm.service.UserProfileService;
import com.nekres.rm.service.UserStorageService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author nekres
 */
@Controller
@RequestMapping("/storage")
public class StorageController {
    private static final Logger logger = Logger.getLogger(StorageController.class.getName());
    @Autowired
    private UserProfileService profileService;
    @Autowired
    private UserStorageService userStorageService;
    
    @RequestMapping(path = "/mkdir", method = RequestMethod.GET)
    public ResponseEntity mkdir(@RequestParam final String key, @RequestParam final String directory){
        if(!profileService.isKeyExist(key))
            throw new NoSuchStorageException();
        userStorageService.mkdir(directory, key);
        logger.info("Invoke successfull");
        return ResponseEntity.ok("Nice.");
    }
    @RequestMapping(path = "/rename", method = RequestMethod.GET)
    public ResponseEntity rename(@RequestParam final String key, @RequestParam final String oldFilePath,
            @RequestParam final String newFilePath){
        boolean created = userStorageService.rename(key, oldFilePath, newFilePath);
        if(created)
            return ResponseEntity.ok(new Response<String>("File renamed.", "success"));
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<String>("Problems while renaming file.","error"));
    }
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleNoSuchStorageException(NoSuchStorageException ne){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>("No storage with such key registered","error"));
    }
    
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleNoFileException(NoSuchFileException nsfe){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>("No such file.", "error"));
    }
}
