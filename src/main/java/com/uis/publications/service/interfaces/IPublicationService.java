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

    DetailPublicationDTO getDetailPublication(Long id_publication, String token);

    List<DetailPublicationDTO> ResponsesInputs(Long id_publication,String token);

    Boolean createPublication(PublicationDTO publicationDTO,String token);

    Boolean createComment(CommentDTO comments,String token);

    Boolean createRate(ScoreDTO scoreDTO,String token);

    Boolean deleteRate(Long id_input,String token );

    List<InputDTO> searchPublication(String description);
}
