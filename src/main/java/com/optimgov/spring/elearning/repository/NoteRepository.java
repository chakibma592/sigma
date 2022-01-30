package com.optimgov.spring.elearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.AnneeUniversitaire;
import com.optimgov.spring.elearning.models.Note;
import com.optimgov.spring.elearning.models.Semestre;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	@Query("SELECT c FROM Note c where c.id = :identity")
	Note findByNoteId(@Param(value="identity")Long id);
	@Query("SELECT c FROM Note c where c.student.id = :identity AND c.annee.id= :annee AND c.semestre.id = :semestre ORDER BY c.element.module.id")
	ArrayList<Note>findByStudentId(@Param(value="identity")Long id, @Param(value="annee")Long annee, @Param(value="semestre")Long semestre);

}
