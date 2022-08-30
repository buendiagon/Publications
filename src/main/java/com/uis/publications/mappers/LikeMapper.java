package com.uis.publications.mappers;

import com.uis.publications.dto.LikeDTO;
import com.uis.publications.model.Like;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
/**
 * @author Juan David Morantes Vergara
 **/
@Mapper
public interface LikeMapper {
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

    //Mapping Dto a Like
    @Mapping(target = "id", source = "id")
    @Mapping(target = "id_user", source = "id_user")
    @Mapping(target = "id_new", source = "id_new")
    @Mapping(target = "id_comment", source = "id_comment")
    @Mapping(target = "is_like", source = "is_like")
    Like toLike(LikeDTO likeDTO);
    //Entidad a DTO
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "id_user", source = "id_user")
    @Mapping(target = "id_new", source = "id_new")
    @Mapping(target = "id_comment", source = "id_comment")
    @Mapping(target = "is_like", source = "is_like")
    LikeDTO toLikeDTO(Like like);

}
