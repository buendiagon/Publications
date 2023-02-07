package com.uis.publications.mappers;

import com.uis.publications.dto.CommentDTO;
import com.uis.publications.model.Input_comments;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.naming.CommunicationException;

/**
 * @autor Juan David Morantes Vergara
 */
@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE= Mappers.getMapper(CommentMapper.class);

    Input_comments toComment(CommentDTO commentDTO);
    CommentDTO toCommentDTO(Input_comments input_comments);
}
