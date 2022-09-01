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
    public ResponseEntity<Page<PublicationsDTO>> getNews(@RequestParam Long id,Pageable pageable){return ResponseEntity.ok(this.publicationsService.getNews(id,pageable));}

    @PostMapping
    public ResponseEntity<Boolean> createPublication(@Valid @RequestBody PublicationsDTO publicationsDTO){
        Boolean isOk = publicationsService.createPublication(publicationsDTO);
        return ResponseEntity.ok(isOk);
    }

    @PutMapping
    public ResponseEntity<Boolean> pullNews(@Valid @RequestBody PublicationsDTO publicationsDTO){ return ResponseEntity.ok(this.publicationsService.pullNews(publicationsDTO));}
}
