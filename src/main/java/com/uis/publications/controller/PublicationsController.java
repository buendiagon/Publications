package com.uis.publications.controller;

import com.uis.publications.dto.LikeDTO;
import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.model.Publication;
import com.uis.publications.service.interfaces.IPublicationsService;
import org.apache.catalina.util.IOTools;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Daniel Adrian Gonzalez Buendia
 * @author Juan David Morantes Vergara
 **/
@RestController
@RequestMapping("/api/news")
public class PublicationsController {


/*    @GetMapping(value = "/")
    public Page<Publication> findAll(@RequestParam Map<String,Object> params, Model model){
        int page= params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1):0;
        PageRequest pageRequest = PageRequest.of(page,10);
        Page<Publication> pagePublication= publicationsService.getAll(pageRequest);

        int totalPage= pagePublication.getTotalPages();
        if(totalPage>0){
            List<Integer> pages= IntStream.rangeClosed(1,totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("list",pagePublication.getContent());
        return pagePublication;
    }*/
    @Autowired
    IPublicationsService publicationsService;

    @GetMapping
    public ResponseEntity<List<PublicationsDTO>> getTrends(){ return ResponseEntity.ok(publicationsService.getTrends());}

    @GetMapping("/{id}")
    public ResponseEntity<List<PublicationsDTO>> getNews(@PathVariable Long id){return ResponseEntity.ok(this.publicationsService.getNews(id));}

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
