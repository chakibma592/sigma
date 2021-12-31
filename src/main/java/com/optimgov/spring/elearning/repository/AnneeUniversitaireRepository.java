package com.optimgov.spring.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.AnneeUniversitaire;

@Repository
public interface AnneeUniversitaireRepository extends JpaRepository<AnneeUniversitaire, Long>{
	@Query("SELECT c FROM AnneeUniversitaire c where c.id = :identity")
	AnneeUniversitaire findByAnneeId(@Param(value="identity")Long id);

}
