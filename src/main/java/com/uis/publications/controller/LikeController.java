package com.uis.publications.controller;

import com.uis.publications.dto.LikeDTO;
import com.uis.publications.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.List;
/**
 * @author Juan David Morantes Vergara
 **/
@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    ILikeService likeService;
/*
    @GetMapping("/all")
    public ResponseEntity<List<LikeDTO>> getLikes(){
        return ResponseEntity.ok(this.likeService.getLikes());
    }
*/

    @PostMapping
    public ResponseEntity<Boolean> createLike(@Valid @RequestBody LikeDTO likeDTO){
        return ResponseEntity.ok(this.likeService.createLike(likeDTO));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteLikeById(@RequestParam Long id){
        return ResponseEntity.ok(this.likeService.deleLikeById(id));
    }


}
