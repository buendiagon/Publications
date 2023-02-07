package com.uis.publications.repository;

import com.uis.publications.model.Input;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @autor Juan David Morantes Vergara
 */
@Repository
public interface IPublicationRepository extends JpaRepository<Input,Long> {

    @Query("FROM Input i WHERE i.is_question = :i")
    Page<Input> findAllQuestions(Pageable pageable,Boolean i);

    @Query("FROM Input i WHERE i.id_career = :i and i.is_question = true")
    Page<Input> findAllQuestionsByCareer(Pageable pageable,Long i);


}
