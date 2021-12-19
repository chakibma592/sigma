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

import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.models.StudiesLevel;
import com.optimgov.spring.elearning.repository.StudiesLevelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application2.properties")
public class StudiesLevelJpaTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	 StudiesLevelRepository levelRepository;

	  @Test
	  public void should_find_no_sudieslevel_if_repository_is_empty() {
	    Iterable<StudiesLevel> levels = levelRepository.findAll();

	    assertThat(levels).isEmpty();
	  }
	  @Test
	  public void should_store_a_profession() {
		  StudiesLevel level = levelRepository.save(new StudiesLevel("Master"));

	    assertThat(level).hasFieldOrPropertyWithValue("levelname", "Master");
	    }
	  @Test
	  public void should_find_all_sudieslevels() {
		  StudiesLevel l1 = new StudiesLevel("Master");
	    entityManager.persist(l1);

	    StudiesLevel l2 = new StudiesLevel("Lisence");
	    entityManager.persist(l2);

	    StudiesLevel l3 = new StudiesLevel("Hight School");
	    entityManager.persist(l3);

	    Iterable<StudiesLevel> levels = levelRepository.findAll();

	    assertThat(levels).hasSize(3).contains(l1, l2, l3);
	  }
	  @Test
	  public void should_find_sudieslevel_by_id() {
		  StudiesLevel l1 = new StudiesLevel("Master");
		    entityManager.persist(l1);

		    StudiesLevel l2 = new StudiesLevel("Lisence");
		    entityManager.persist(l2);

		    StudiesLevel foundLevel= levelRepository.findById(l2.getId()).get();

	    assertThat(foundLevel).isEqualTo(l2);
	  }

	  

	  @Test
	  public void should_update_sudieslevel_by_id() {
		  StudiesLevel l1 = new StudiesLevel("Master");
		    entityManager.persist(l1);

		    StudiesLevel l2 = new StudiesLevel("Lisence");
		    entityManager.persist(l2);

		    StudiesLevel updatedLevel = new StudiesLevel("Hight School");

		    StudiesLevel level = levelRepository.findById(l1.getId()).get();
	    level.setLevelname(updatedLevel.getLevelname());
	   
	    levelRepository.save(level);

	    StudiesLevel checklevel = levelRepository.findById(l1.getId()).get();
	    
	    assertThat(checklevel.getId()).isEqualTo(l1.getId());
	    assertThat(checklevel.getLevelname()).isEqualTo(updatedLevel.getLevelname());
	  }

	  @Test
	  public void should_delete_sudieslevel_by_id() {
		  StudiesLevel l1 = new StudiesLevel("Master");
		    entityManager.persist(l1);

		    StudiesLevel l2 = new StudiesLevel("Lisence");
		    entityManager.persist(l2);
		    
		    StudiesLevel l3 = new StudiesLevel("Doctorate");
		    entityManager.persist(l3);
		    levelRepository.deleteById(l2.getId());

	    Iterable<StudiesLevel> levels = levelRepository.findAll();

	    assertThat(levels).hasSize(2).contains(l1, l3);
	  }

	  @Test
	  public void should_delete_all_sudieslevel() {
	    entityManager.persist(new Profession("Master"));
	    entityManager.persist(new Profession("Lisence"));

	    levelRepository.deleteAll();

	    assertThat(levelRepository.findAll()).isEmpty();
	  }
}
