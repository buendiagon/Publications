package com.uis.publications.mappers;

import com.uis.publications.dto.InputDTO;
import com.uis.publications.dto.PublicationDTO;
import com.uis.publications.model.Input;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @autor Juan David Morantes Vergara
 */
@Mapper
public interface InputMapper {
    InputMapper INSTANCE= Mappers.getMapper(InputMapper.class);

    InputDTO toInputDTO(Input input);
    Input toInput(InputDTO inputDTO);
    Input toInput(PublicationDTO publicationDTO);
}
