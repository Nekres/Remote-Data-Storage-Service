/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.controller;

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
public class StorageController {
    
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    @ResponseBody
    public String createStorage(@RequestParam("email") String email, @RequestParam String password){
        return null;
    }
}
