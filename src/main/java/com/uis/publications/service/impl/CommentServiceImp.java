/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.service.impl;

import com.uis.publications.dto.CommentDTO;
import com.uis.publications.mappers.CommentMapper;
import com.uis.publications.model.Comment;
import com.uis.publications.model.User;
import com.uis.publications.repository.ICommentRepository;
import com.uis.publications.repository.IUserRepository;
import com.uis.publications.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements ICommentService {
    @Autowired
    ICommentRepository commentRepository;

    @Autowired
    IUserRepository userRepository;


    @Override
    public List<CommentDTO> getCommentsByIdPublication(Long idPublication) {
        List<Comment> geComment = commentRepository.findAll();
        List<CommentDTO> gecoments = geComment.stream()
                .map(CommentMapper.INSTANCE::toCommentDTO).collect(Collectors.toList());
        ArrayList<CommentDTO >listComents = new ArrayList<>();
        List<User> users=userRepository.findAll();
        for (CommentDTO commentDTO : gecoments) {
            if(commentDTO.getId_new().equals(idPublication)) {
                if (commentDTO.getId_parent() == null) {
                    for (CommentDTO commentDTO1 : gecoments) {
                        if (commentDTO1.getId_parent() != null) {
                            if (commentDTO1.getId_parent().equals(commentDTO.getId())) {
                                for(User user:users){
                                    if(user.getId().equals(commentDTO1.getId_user())){
                                        commentDTO1.setNames(user.getNames());
                                        commentDTO1.setLastNames(user.getLastNames());
                                    }
                                }
                                commentDTO.setReplies(commentDTO1);
                                break;
                            }
                        }
                    }
                    for(User user:users){
                        if(user.getId().equals(commentDTO.getId_user())){
                            commentDTO.setNames(user.getNames());
                            commentDTO.setLastNames(user.getLastNames());
                        }
                    }
                    listComents.add(commentDTO);
                }
           }
        }

        return listComents;
    }
    @Override
    public CommentDTO createCommnet(CommentDTO commentDTO) {
        return CommentMapper.INSTANCE.toCommentDTO(
                this.commentRepository.save(CommentMapper.INSTANCE.toComment(commentDTO)));
    }

}
