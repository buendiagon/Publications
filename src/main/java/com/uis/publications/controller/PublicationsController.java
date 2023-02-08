package com.uis.publications.controller;

import com.uis.publications.dto.CommentDTO;
import com.uis.publications.dto.DetailPublicationDTO;
import com.uis.publications.dto.PublicationDTO;
import com.uis.publications.dto.ScoreDTO;
import com.uis.publications.model.Input_comments;
import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping("/id_career/{id}")
    public ResponseEntity<Map<String,Object>> getPublicationsByCareer(@PathVariable Long id,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(publicationService.getPublicationsByCareer(page, size, id));
    }
    @GetMapping("/id_publication/{id}")
    public ResponseEntity<DetailPublicationDTO> getDetailPublication(@PathVariable Long id){
        return ResponseEntity.ok(publicationService.getDetailPublication(id));
    }
    @PostMapping
    public ResponseEntity<Boolean> createPublication(@Valid @RequestBody PublicationDTO publicationsDTO){
        return ResponseEntity.ok(publicationService.createPublication(publicationsDTO));
    }
    @PostMapping("/comments")
    public ResponseEntity<Boolean> createComment(@Valid @RequestBody CommentDTO comments){
        return ResponseEntity.ok(publicationService.createComment(comments));
    }
    @PostMapping("/score")
    public ResponseEntity<Boolean> createRate(@Valid @RequestBody ScoreDTO scoreDTO){
        return ResponseEntity.ok(publicationService.createRate(scoreDTO));
    }
    @DeleteMapping("/score/{id}")
    public ResponseEntity<Boolean> deleteRate(@Valid @PathVariable Long id){
        return ResponseEntity.ok(publicationService.deleteRate(id));
    }
 }
