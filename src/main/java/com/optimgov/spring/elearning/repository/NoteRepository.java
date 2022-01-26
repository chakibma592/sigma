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
	@Query("SELECT c FROM Note c where c.studentid = :identity AND c.annee= :annee AND c.semestre= :semestre")
	ArrayList<Note>findByStudentId(@Param(value="identity")Long id, @Param(value="annee")AnneeUniversitaire annee, @Param(value="semestre")Semestre semestre);

}
