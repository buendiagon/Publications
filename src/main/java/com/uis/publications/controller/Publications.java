package com.uis.publications.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel Adrian Gonzalez Buendia
 **/
@RestController
@RequestMapping("/api")
public class Publications {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello World, and welcome to social", HttpStatus.OK);
    }
}
