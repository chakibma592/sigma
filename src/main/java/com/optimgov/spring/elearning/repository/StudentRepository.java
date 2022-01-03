package com.optimgov.spring.elearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	 Optional<Student> findByUsername(String username);
	  Boolean existsByNumappogee(String numappogee);
	  Boolean existsByEmail(String email);
	  @Query("SELECT u FROM Student u where u.id = :identity")
	  Student findByStudentId(@Param(value="identity")Long id);
}
