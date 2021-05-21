package com.optimgov.spring.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
