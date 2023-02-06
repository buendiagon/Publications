package com.uis.publications.repository;

import com.uis.publications.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @autor Juan David Morantes Vergara
 */
@Repository
public interface IPublicationRepository extends JpaRepository<Publication,Long> {
}
