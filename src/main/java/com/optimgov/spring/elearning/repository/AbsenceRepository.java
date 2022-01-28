package com.optimgov.spring.elearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.Absence;
import com.optimgov.spring.elearning.models.Note;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{
	@Query("SELECT c FROM Absence c where c.id = :identity")
	Absence findByAbsenceId(@Param(value="identity")Long id);
	@Query("SELECT c FROM Absence c where c.student.id = :identity AND c.annee.id= :annee AND c.semestre.id = :semestre")
	ArrayList<Absence>findByStudentId(@Param(value="identity")Long id, @Param(value="annee")Long annee, @Param(value="semestre")Long semestre);
	
}
