package com.optimgov.spring.elearning.repository;

import java.awt.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimgov.spring.elearning.models.Lesson;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>{
	@Query("SELECT l  FROM Lesson l where l.course.id = :identity")
	ArrayList<Lesson> findByCourse(@Param(value="identity")Long id);
}
