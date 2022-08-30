package com.uis.publications.repository;



import com.uis.publications.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Juan David Morantes Vergara
 **/
import java.util.Optional;
@Repository
public interface IPublicationsRepository extends JpaRepository<Publication, Long> {

}
