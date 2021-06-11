package com.optimgov.spring.elearning.repository;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.optimgov.spring.elearning.models.Question;;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query("SELECT q  FROM Question q where q.quiz.id = :identity")
	ArrayList<Question> findByQuiz(@Param(value="identity")Long id);
}
