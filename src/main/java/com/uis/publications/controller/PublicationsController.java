package com.uis.publications.controller;

import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @autor Juan David Morantes Vergara
 */
@RestController
@RequestMapping("/api/publications")
public class PublicationsController {
    @Autowired
    private IPublicationService publicationService;
    @GetMapping
    public Map<String,Object> getPublications(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size){
        Map<String,Object> r=publicationService.getPublications(page,size);
        return r;
    }
}
