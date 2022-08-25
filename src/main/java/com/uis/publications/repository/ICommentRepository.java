/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.repository;

import com.uis.publications.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
