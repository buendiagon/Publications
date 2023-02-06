package com.uis.publications.service.impl;

import com.uis.publications.exception.TransactionException;
import com.uis.publications.model.Publication;
import com.uis.publications.repository.IPublicationRepository;
import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public class PublicationServiceImpl implements IPublicationService {
    @Autowired
    private IPublicationRepository publicationRepository;
    @Autowired
    public void SetPublicationRepository(IPublicationRepository publicationRepository) {
        this.publicationRepository=publicationRepository;
    }
    public Map<String, Object> getPublications(int page, int size){
        try {
            List<Publication> publicationList;
            Pageable paging = PageRequest.of(page, size);

            Page<Publication> pageTuts;
            pageTuts = publicationRepository.findAll(PageRequest.of(page, size, Sort.by("score").descending()));
            publicationList = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("publications", publicationList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return response;
        }catch (Exception e) {
            throw new TransactionException("INTERNAL PROBLEM");
        }

    }

}
