package com.uis.publications.controller;

import com.uis.publications.dto.*;
import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @autor Juan David Morantes Vergara
 */
@RestController
@RequestMapping("/api")
public class PublicationsController {

    private IPublicationService publicationService;
    @GetMapping
    public ResponseEntity<Map<String,Object>> getPublications(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(publicationService.getPublications(page,size));
    }
    @GetMapping("/id_career/{id}")
    public ResponseEntity<Map<String,Object>> getPublicationsByCareer(@PathVariable Long id,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(publicationService.getPublicationsByCareer(page, size, id));
    }
    @GetMapping("/id_publication/{id}")
    public ResponseEntity<DetailPublicationDTO> getDetailPublication(@PathVariable Long id,
                                                                     @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(publicationService.getDetailPublication(id,token));
    }
    @PostMapping
    public ResponseEntity<Boolean> createPublication(@Valid @RequestBody PublicationDTO publicationsDTO,
                                                     @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(publicationService.createPublication(publicationsDTO,token));
    }
    @PostMapping("/comments")
    public ResponseEntity<Boolean> createComment(@Valid @RequestBody CommentDTO comments,
                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(publicationService.createComment(comments,token));
    }
//    Holo
    @PostMapping("/score")
    public ResponseEntity<Boolean> createRate(@Valid @RequestBody ScoreDTO scoreDTO,
                                              @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(publicationService.createRate(scoreDTO,token));
    }
    @DeleteMapping("/score/{id_input}")
    public ResponseEntity<Boolean> deleteRate(@Valid @PathVariable Long id_input,
                                              @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(publicationService.deleteRate(id_input,token));
    }

    @GetMapping("/search")
    public ResponseEntity<List<InputDTO>> searchPublication(@RequestParam String search){
        return ResponseEntity.ok(publicationService.searchPublication(search));
    }

    @Autowired
    @Qualifier("publicationServiceImpl")
    public void setPublicationService(IPublicationService publicationService) {
        this.publicationService = publicationService;
    }
 }
