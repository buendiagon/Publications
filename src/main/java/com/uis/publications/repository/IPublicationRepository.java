package com.uis.publications.repository;

import com.uis.publications.model.Input;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autor Juan David Morantes Vergara
 */
@Repository
public interface IPublicationRepository extends JpaRepository<Input,Long> {

    @Query("FROM Input i WHERE i.is_question = :i")
    Page<Input> findAllQuestions(Pageable pageable,Boolean i);

    @Query("FROM Input i WHERE i.id_career = :i and i.is_question = true")
    Page<Input> findAllQuestionsByCareer(Pageable pageable,Long i);

    @Query("FROM Input i WHERE i.is_question = false and i.id_parent=:i")
    List<Input> findAllResponses(Long i);

    @Query("FROM Input i WHERE fts(:description, :description) = true AND i.is_question = true")
    List<Input> findAllByDescription(String description);

    @Query("From Input i where i.id_user=:id_user")
    List<Input> findById_user(Long id_user);


}
