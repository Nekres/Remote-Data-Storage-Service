/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.controller;

import com.nekres.rm.exceptions.*;
import com.nekres.rm.pojo.response.Response;
import com.nekres.rm.service.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>("No storage with such key registered","error"));
        userStorageService.mkdir(directory, key);
        logger.info("mkdir " + directory);
        return ResponseEntity.ok(new Response<String>("Directory " + directory + "successfully created", "success"));
    }
    @RequestMapping(path = "/rename", method = RequestMethod.GET)
    public ResponseEntity rename(@RequestParam final String key, @RequestParam final String oldFilePath,
            @RequestParam final String newFilePath){
        boolean created = userStorageService.rename(key, oldFilePath, newFilePath);
        if(created)
            return ResponseEntity.ok(new Response<String>("File renamed.", "success"));
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<String>("Problems while renaming file.","error"));
    }
    @RequestMapping(path = "uploadFile", method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("file") MultipartFile file, @RequestParam("target") String targetDir, @RequestParam("key") String key, RedirectAttributes attributes){
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>("No file selected", "error"));
        }
        try{
            byte bytes[] = file.getBytes();
            StringBuilder builder = new StringBuilder(key);
            builder.append("/");
            builder.append(targetDir);
            builder.append("/");
            builder.append(file.getOriginalFilename());
            Path path = Paths.get(builder.toString());
            
            Files.write(path, bytes);
        }catch(IOException iox){
            iox.printStackTrace();
            return ResponseEntity.badRequest().body(new Response<String>("File uploading failed.", "error"));
        }
        return ResponseEntity.ok(new Response<String>("File uploaded.","success"));
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
