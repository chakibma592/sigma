package com.inpt.spring.note.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inpt.spring.note.models.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long>{
	
	@Query("SELECT l  FROM Inscription l where l.student.id = :identity AND l.observation= 'En_cours'")
	Inscription findInscriptionEnCoursByStudent(@Param(value="identity")Long id);
}
