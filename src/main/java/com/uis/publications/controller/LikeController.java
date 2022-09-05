package com.uis.publications.controller;

import com.uis.publications.dto.LikeDTO;
import com.uis.publications.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Juan David Morantes Vergara
 **/
@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    ILikeService likeService;

    @GetMapping("/all")
    public int getLikesByIdPublication(@RequestParam Long idPublic){
        return this.likeService.getLikesByIdPublication(idPublic);
    }


    @PostMapping
    public ResponseEntity<Boolean> createLike(@Valid @RequestBody LikeDTO likeDTO){
        return ResponseEntity.ok(this.likeService.createLike(likeDTO));
    }

    @DeleteMapping("/deleteLike")
    public ResponseEntity<Boolean> deleteLikeById(@RequestParam Long idPublicacion, @RequestParam Long idUser){
        return ResponseEntity.ok(this.likeService.deleteLikeByLikeDTO(idPublicacion,idUser));
    }


}
