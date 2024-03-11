package com.tutorhub.repository;

import com.tutorhub.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

  @Query(
      value =
          """
              UPDATE users
              SET enabled = true
              WHERE username = :username
              """,
      nativeQuery = true)
  @Modifying
  void enable(String username);

  @Query(
      value =
          """
              UPDATE users
              SET password = :password
              WHERE username = :username
              """,
      nativeQuery = true)
  @Modifying
  void resetPassword(String password, String username);
}
