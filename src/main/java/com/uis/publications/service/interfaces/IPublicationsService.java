package com.uis.publications.service.interfaces;

import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.model.User;

import java.util.List;

/**
 * @author Juan David Morantes Vergara
 **/
public interface IPublicationsService {
    List<PublicationsDTO>  getPublications();
    List<PublicationsDTO> getNews(Long id);

    Boolean createPublication(PublicationsDTO publicationsDTO);

    Boolean pullNews(PublicationsDTO publicationsDTO);


    List<User> getUsers();
}
