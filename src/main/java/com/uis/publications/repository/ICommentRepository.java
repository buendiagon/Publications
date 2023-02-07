package com.uis.publications.repository;

import com.uis.publications.model.Input_comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autor Juan David Morantes Vergara
 */
@Repository
public interface ICommentRepository extends JpaRepository<Input_comments,Long> {

    @Query("FROM Input_comments i WHERE i.id_input = :i")
    List<Input_comments> findByIdInput(Long i);
}
