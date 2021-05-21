package com.optimgov.spring.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long>{

}
