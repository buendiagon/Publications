package com.uis.publications.controller;

import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.service.interfaces.IPublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Daniel Adrian Gonzalez Buendia
 * @author Juan David Morantes Vergara
 **/
@RestController
@RequestMapping("/api/news")
public class PublicationsController {

    @Autowired
    IPublicationsService publicationsService;

    @GetMapping("/pagueable")
    public ResponseEntity<Page<PublicationsDTO>> getTrends(Pageable pageable){ return ResponseEntity.ok(publicationsService.getTrends(pageable));}

    @GetMapping("/pagueable/{id}")
    public ResponseEntity<Page<PublicationsDTO>> getNews(@PathVariable Long id,Pageable pageable){return ResponseEntity.ok(this.publicationsService.getNews(id,pageable));}

    @PostMapping
    public ResponseEntity<PublicationsDTO> createPublication(@Valid @RequestBody PublicationsDTO publicationsDTO){
        publicationsDTO=publicationsService.createPublication(publicationsDTO);
        return ResponseEntity.ok(publicationsDTO);
    }
/*    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello World, and welcome to k-pop social", HttpStatus.OK);
    }*/
}
