package com.uis.publications.repository;

import com.uis.publications.model.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Daniel Adrian Gonzalez Buendia
 */

public interface IAccessControlRepository extends JpaRepository<AccessControl, Long> {

    Optional<AccessControl> findDistinctTopByIdUser(Long userId);

}
