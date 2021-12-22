package com.optimgov.spring.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.Course;
import com.optimgov.spring.elearning.models.Filiere;

@Repository
public interface FiliereRepository  extends JpaRepository<Filiere, Long>{
	@Query("SELECT c FROM Filiere c where c.id = :identity")
	Filiere findByFiliereId(@Param(value="identity")Long id);
}
