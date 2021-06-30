package com.optimgov.spring.elearning.repository;

import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.Language;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

}
