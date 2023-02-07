package com.uis.publications.controller;

import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @autor Juan David Morantes Vergara
 */
@RestController
@RequestMapping("/api")
public class PublicationsController {

    private IPublicationService publicationService;
    @Autowired
    @Qualifier("publicationServiceImpl")
    public void setPublicationService(IPublicationService publicationService) {
        this.publicationService = publicationService;
    }
    @GetMapping
    public ResponseEntity<Map<String,Object>> getPublications(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(publicationService.getPublications(page,size));
    }
    @GetMapping("/id_career")
    public ResponseEntity<Map<String,Object>> getPublicationsByCareer(@RequestParam Long id_career,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(publicationService.getPublicationsByCareer(page, size, id_career));
    }
 }
