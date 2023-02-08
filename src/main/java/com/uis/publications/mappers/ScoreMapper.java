package com.uis.publications.mappers;

import com.uis.publications.dto.ScoreDTO;
import com.uis.publications.model.Score;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @autor Juan David Morantes Vergara
 */
@Mapper
public interface ScoreMapper {
    ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);
    Score toScore(ScoreDTO scoreDTO);
    ScoreDTO toScoreDTO(Score score);
}
