package com.uis.publications.mappers;

import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.model.Publication;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
/**
 * @author Juan David Morantes Vergara
 **/
@Mapper
public interface PublicationsMapper {
    PublicationsMapper INSTANCE = Mappers.getMapper(PublicationsMapper.class);

    //Mapping Dto a Example
    @Mapping(target = "id", source = "id")
    @Mapping(target = "id_user", source = "id_user")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "photo_url", source = "photo_url")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "updated", source = "updated")
    Publication toPublication(PublicationsDTO publicationsDTO);

    //Entidad a DTO
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "id_user", source = "id_user")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "photo_url", source = "photo_url")
    @Mapping(target = "created", source = "created")
    @Mapping(target = "updated", source = "updated")
    PublicationsDTO toPublicationsDTO(Publication Publication);

}
