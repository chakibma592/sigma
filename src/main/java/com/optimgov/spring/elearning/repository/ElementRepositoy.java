package com.optimgov.spring.elearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.ElementModule;

@Repository
public interface ElementRepositoy extends JpaRepository<ElementModule, Long>{
	@Query("SELECT l  FROM ElementModule l where l.module.id = :identity")
	ArrayList<ElementModule> findElementByModule(@Param(value="identity")Long id);
}

