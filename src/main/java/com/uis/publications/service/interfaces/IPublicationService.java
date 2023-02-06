package com.uis.publications.service.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @autor Juan David Morantes Vergara
 */
public interface IPublicationService {
    Map<String, Object> getPublications(int page, int size);
}
