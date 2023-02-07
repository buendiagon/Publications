package com.uis.publications.mappers;

import com.uis.publications.dto.DetailPublicationDTO;
import com.uis.publications.dto.InputDTO;
import com.uis.publications.model.Input;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @autor Juan David Morantes Vergara
 */
@Mapper
public interface DetailPublicationMapper {
    DetailPublicationMapper INSTANCE= Mappers.getMapper(DetailPublicationMapper.class);

    DetailPublicationDTO toDetailPublicationDTO(Input inputDTO);

    Input toInputDTO(DetailPublicationDTO detailPublicationDTO);
}
