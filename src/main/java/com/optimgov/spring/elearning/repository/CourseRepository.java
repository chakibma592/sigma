package com.optimgov.spring.elearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Course;

@Repository
public interface CourseRepository  extends JpaRepository<Course, Long>{
	@Query("SELECT c  FROM Course c where c.topic.id = :identity AND c.locked=false")
	ArrayList<Course> findByTopic(@Param(value="identity")Long id);
	@Query("SELECT c FROM Course c where c.id = :identity")
	Course findByCourseId(@Param(value="identity")Long id);
	

}
