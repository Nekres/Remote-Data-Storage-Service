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
import java.util.ArrayList;
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
        return ResponseEntity.ok(new Response<String>("Directory " + directory + " successfully created", "success"));
    }
    @RequestMapping(path = "/rename", method = RequestMethod.GET)
    public ResponseEntity rename(@RequestParam final String key, @RequestParam final String oldFilePath,
            @RequestParam final String newFilePath){
        boolean created = userStorageService.rename(key, oldFilePath, newFilePath);
        if(created)
            return ResponseEntity.ok(new Response<String>("File renamed.", "success"));
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<String>("Problems while renaming file.","error"));
    }
    @RequestMapping(path = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("file") MultipartFile file, @RequestParam("target") String targetDir, @RequestParam("key") String key, RedirectAttributes attributes){
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>("No file selected", "error"));
        }
        userStorageService.uploadFile(file, key, targetDir);
        return ResponseEntity.ok(new Response<String>("File uploaded.","success"));
    }
    @RequestMapping(path = "/explore", method = RequestMethod.GET)
    public ResponseEntity explore(@RequestParam String key){
        return ResponseEntity.ok(userStorageService.explore(key));
    }
    @RequestMapping(path = "/move", method = RequestMethod.GET)
    public ResponseEntity move(@RequestParam String sourceFile,@RequestParam("new_file_name") String newFileName, @RequestParam String destination, @RequestParam String key){
        userStorageService.move(sourceFile,newFileName, destination, key);
        return ResponseEntity.ok(new Response<String>("file successfully moved to " + destination, "success"));
    }
    @RequestMapping(path= "/search", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam String file, @RequestParam String key){
        ArrayList<String> searchList = userStorageService.search(file, key);
        return ResponseEntity.ok(new Response<ArrayList<String>>(searchList,"success"));
    }
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleNoSuchStorageException(NoSuchStorageException ne){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>(ne.getMessage(),"error"));
    }
    
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleNoFileException(NoSuchFileException nsfe){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<String>(nsfe.getMessage(), "error"));
    }
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleFileAlreadyExistException(FileAlreadyExistException alreadyExistException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<String>(alreadyExistException.getMessage(),"error"));
    }
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleNoSuchDirectoryException(NoSuchDirectoryException nsde){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response<String>(nsde.getMessage(),"error"));
    }
    
}
