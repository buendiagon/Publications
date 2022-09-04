package com.uis.publications.controller;

import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.service.interfaces.IPublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<List<PublicationsDTO>> getTrends(){ return ResponseEntity.ok(publicationsService.getPublications());}

    @GetMapping("/pagueableById")
    public ResponseEntity<List<PublicationsDTO>> getNews(@RequestParam Long idUser){return ResponseEntity.ok(this.publicationsService.getNews(idUser));}

    @PostMapping
    public ResponseEntity<Boolean> createPublication(@Valid @RequestBody PublicationsDTO publicationsDTO){
        Boolean isOk = publicationsService.createPublication(publicationsDTO);
        return ResponseEntity.ok(isOk);
    }

    @PutMapping
    public ResponseEntity<Boolean> pullNews(@Valid @RequestBody PublicationsDTO publicationsDTO){ return ResponseEntity.ok(this.publicationsService.pullNews(publicationsDTO));}
}
