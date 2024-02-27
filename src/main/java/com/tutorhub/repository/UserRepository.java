package com.tutorhub.repository;

import com.tutorhub.model.user.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  Page<User> findAll(Pageable page);
  @Query(
      value =
          """
              SELECT exists(
                SELECT 1
                FROM users u LEFT JOIN course c
                ON u.id = c.user_id
                WHERE u.id = :userId
                AND c.id = :courseId
              )
              """,
      nativeQuery = true)
  boolean isCourseOwner(Long userId, Long courseId);
}
