package com.optimgov.spring.elearning;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.optimgov.spring.elearning.models.Level;
import com.optimgov.spring.elearning.repository.LevelRepository;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application2.properties")
public class LevelJpaTest {
	 @Autowired
	  private TestEntityManager entityManager;
	 @Autowired
	 LevelRepository levelRepository;

	  @Test
	  public void should_find_no_levels_if_repository_is_empty() {
	    Iterable<Level> levels = levelRepository.findAll();
	    assertThat(levels).isEmpty();
	  }
	  @Test
	  public void should_store_a_level() {
	    Level level = levelRepository.save(new Level("Debutant"));
	    assertThat(level).hasFieldOrPropertyWithValue("levelname", "Debutant");
	    }
	  @Test
	  public void should_find_all_levels() {
		  Level p1 = new Level("Debutant");
	    entityManager.persist(p1);

	    Level p2 = new Level("Intermediare");
	    entityManager.persist(p2);

	    Level p3 = new Level("Experimenté");
	    entityManager.persist(p3);

	    Iterable <Level> levels = levelRepository.findAll();

	    assertThat(levels).hasSize(3).contains(p1, p2, p3);
	  }
	  @Test
	  public void should_find_level_by_id() {
		  Level p1 = new  Level("Debutant");
		    entityManager.persist(p1);

		    Level p2 = new  Level("Experimanté");
		    entityManager.persist(p2);

		    Level foundLevel= levelRepository.findById(p2.getId()).get();

	    assertThat(foundLevel).isEqualTo(p2);
	  }

	  

	  @Test
	  public void should_update_level_by_id() {
		  Level p1 = new  Level("Debutant");
		    entityManager.persist(p1);

		  Level p2 = new  Level("Intermediare");
		    entityManager.persist(p2);

		    Level updatedLevel = new  Level("Experimenté");

		    Level level = levelRepository.findById(p1.getId()).get();
	    level.setLevelname(updatedLevel.getLevelname());
	   
	    levelRepository.save(level);

	    Level checkLevel = levelRepository.findById(p1.getId()).get();
	    
	    assertThat(checkLevel.getId()).isEqualTo(p1.getId());
	    assertThat(checkLevel.getLevelname()).isEqualTo(updatedLevel.getLevelname());
	  }

	  @Test
	  public void should_delete_level_by_id() {
		  Level p1 = new  Level("Debutant");
		    entityManager.persist(p1);

		  Level p2 = new  Level("Intermediare");
		    entityManager.persist(p2);
		    
		  Level p3 = new  Level("Experimenté");
		    entityManager.persist(p3);
		    levelRepository.deleteById(p2.getId());

	    Iterable<Level> levels = levelRepository.findAll();

	    assertThat(levels).hasSize(2).contains(p1, p3);
	  }

	  @Test
	  public void should_delete_all_levels() {
	    entityManager.persist(new Level("Debutant"));
	    entityManager.persist(new Level("Intermediare"));
	    levelRepository.deleteAll();
	    assertThat(levelRepository.findAll()).isEmpty();
	  }
}
