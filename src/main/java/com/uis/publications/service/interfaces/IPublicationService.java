package com.uis.publications.service.interfaces;


import com.uis.publications.dto.*;
import com.uis.publications.model.Input;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public interface IPublicationService {
    Map<String, Object> getPublications(int page, int size);
    Map<String, Object> getPublicationsByCareer(int page, int size,Long id);

    List<InputDTO> getDataUser(List<Input> inputList);

    DetailPublicationDTO getDetailPublication(Long id_publication);

    List<DetailPublicationDTO> ResponsesInputs(Long id_publication);

    Boolean createPublication(PublicationDTO publicationDTO);

    Boolean createComment(CommentDTO comments);

    Boolean createRate(ScoreDTO scoreDTO,String token);

    Boolean deleteRate(Long id);
}
