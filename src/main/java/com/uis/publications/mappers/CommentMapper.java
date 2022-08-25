/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.mappers;

import com.uis.publications.dto.CommentDTO;
import com.uis.publications.model.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE= Mappers.getMapper(CommentMapper.class);

    //Mapping Dto a Example
    @Mapping(target = "id", source = "id")
    @Mapping(target = "id_user", source = "id_user")
    @Mapping(target = "id_new", source = "id_new")
    @Mapping(target = "id_parent", source = "id_parent")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "updated", source = "updated")
    Comment toComment(CommentDTO commentDTO);

    //Entidad a DTO
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "id_user", source = "id_user")
    @Mapping(target = "id_new", source = "id_new")
    @Mapping(target = "id_parent", source = "id_parent")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "updated", source = "updated")
    CommentDTO toCommentDTO(Comment comment);
}
