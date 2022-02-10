package com.inpt.spring.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inpt.spring.note.models.Semestre;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long>{
	@Query("SELECT c FROM Semestre c where c.id = :identity")
	Semestre findBySemestreId(@Param(value="identity")Long id);

}
