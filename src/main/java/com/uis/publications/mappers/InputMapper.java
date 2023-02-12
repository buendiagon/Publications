package com.uis.publications.mappers;

import com.uis.publications.dto.InputDTO;
import com.uis.publications.dto.PublicationDTO;
import com.uis.publications.model.Input;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @autor Juan David Morantes Vergara
 */
@Mapper
public interface InputMapper {
    InputMapper INSTANCE= Mappers.getMapper(InputMapper.class);

    InputDTO toInputDTO(Input input);
    Input toInput(InputDTO inputDTO);
    Input toInput(PublicationDTO publicationDTO);
    PublicationDTO toPublicationDTO(Input input);
    List<PublicationDTO> toPublicationDTOList(List<Input> inputList);
    List<InputDTO> toInputDTOList(List<Input> inputList);
}
