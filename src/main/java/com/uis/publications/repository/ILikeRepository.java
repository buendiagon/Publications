package com.uis.publications.repository;

import com.uis.publications.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author Juan David Morantes Vergara
 **/
@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
}
