package com.optimgov.spring.elearning.repository;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
	@Query("SELECT a  FROM Answer a where a.question.id = :identity")
	ArrayList<Answer> findByQuestion(@Param(value="identity")Long id);
}
