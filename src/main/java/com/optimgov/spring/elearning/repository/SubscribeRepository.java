package com.optimgov.spring.elearning.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long>{
	@Query("SELECT s FROM Subscribe s where s.user.id = :identity")
	ArrayList <Subscribe> findByCourseBySubscriberId(@Param(value="identity")Long id);
}
