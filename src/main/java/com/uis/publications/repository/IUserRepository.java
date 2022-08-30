package com.uis.publications.repository;

import com.uis.publications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Daniel Adrian Gonzalez Buendia
 **/
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("FROM User u WHERE upper(u.username) = upper(:username)")
    Optional<User> findTopByUsername(String username);
}
