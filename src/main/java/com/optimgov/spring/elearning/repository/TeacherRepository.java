package com.optimgov.spring.elearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Teacher;
import com.optimgov.spring.elearning.models.User;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	  Optional<Teacher> findByUsername(String username);
	  Boolean existsByUsername(String username);
	  Boolean existsByEmail(String email);
	  @Query("SELECT u FROM Teacher u where u.id = :identity")
	  Teacher findByTeacherId(@Param(value="identity")Long id);

}
