/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.service.impl;

import com.uis.publications.dto.CommentDTO;
import com.uis.publications.mappers.CommentMapper;
import com.uis.publications.model.Comment;
import com.uis.publications.repository.ICommentRepository;
import com.uis.publications.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements ICommentService {

    ICommentRepository commentRepository;


    @Override
    public List<CommentDTO> getComments() {
        List<Comment> geComment =  commentRepository.findAll();
        return geComment.stream()
                .map(CommentMapper.INSTANCE::toCommentDTO).collect(Collectors.toList());
    }
    @Override
    public CommentDTO createCommnet(CommentDTO commentDTO) {
        return CommentMapper.INSTANCE.toCommentDTO(
                this.commentRepository.save(CommentMapper.INSTANCE.toComment(commentDTO)));
    }

}
