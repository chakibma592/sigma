package com.inpt.spring.note.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inpt.spring.note.models.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{
	@Query("SELECT l  FROM Module l where l.filiere.id = :identity")
	ArrayList<Module> findModuleByFiliere(@Param(value="identity")Long id);
}
