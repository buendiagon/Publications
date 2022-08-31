/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.controller;
import com.uis.publications.dto.CommentDTO;
import com.uis.publications.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;
/*
    @GetMapping("/all")
    public ResponseEntity<List<CommentDTO>> getComments(){return ResponseEntity.ok(commentService.getComments());}
*/

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO){
        commentDTO=commentService.createCommnet(commentDTO);
        return ResponseEntity.ok(commentDTO);
    }
}
