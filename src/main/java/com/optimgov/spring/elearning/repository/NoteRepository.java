package com.optimgov.spring.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	@Query("SELECT c FROM Note c where c.id = :identity")
	Note findByNoteId(@Param(value="identity")Long id);

}
