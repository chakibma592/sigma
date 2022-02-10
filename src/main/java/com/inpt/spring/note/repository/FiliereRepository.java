package com.inpt.spring.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inpt.spring.note.models.Filiere;

@Repository
public interface FiliereRepository  extends JpaRepository<Filiere, Long>{
	@Query("SELECT c FROM Filiere c where c.id = :identity")
	Filiere findByFiliereId(@Param(value="identity")Long id);
}
