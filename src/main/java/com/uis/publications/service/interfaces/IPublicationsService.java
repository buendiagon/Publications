package com.uis.publications.service.interfaces;

import com.uis.publications.dto.PublicationsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Juan David Morantes Vergara
 **/
public interface IPublicationsService {
    Page<PublicationsDTO> getTrends(Pageable pageable);
    Page<PublicationsDTO>getNews(Long id,Pageable pageable);

    PublicationsDTO createPublication(PublicationsDTO publicationsDTO);
}
