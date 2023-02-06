package com.uis.publications.mappers;

import com.uis.publications.dto.CareerDTO;
import com.uis.publications.model.Career;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @autor Juan David Morantes Vergara
 */
@Mapper
public interface CareerMapper {
    CareerMapper INSTANCE= Mappers.getMapper(CareerMapper.class);

    CareerDTO toCarrerDTO(Career career);
    Career toCareer(CareerDTO careerDTO);
}
