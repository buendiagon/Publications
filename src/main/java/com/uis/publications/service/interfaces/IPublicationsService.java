package com.uis.publications.service.interfaces;

import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
/**
 * @author Juan David Morantes Vergara
 **/
public interface IPublicationsService {
     Page<Publication> getAll(Pageable pageable);
    List<PublicationsDTO> getTrends();

    List<PublicationsDTO>getNews(Long id);

    PublicationsDTO createPublication(PublicationsDTO publicationsDTO);
}
