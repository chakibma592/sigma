package com.optimgov.spring.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Semestre;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long>{
	@Query("SELECT c FROM Semestre c where c.id = :identity")
	Semestre findBySemestreId(@Param(value="identity")Long id);

}
