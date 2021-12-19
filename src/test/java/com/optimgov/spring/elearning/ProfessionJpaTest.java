package com.optimgov.spring.elearning;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.repository.ProfessionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application2.properties")
public class ProfessionJpaTest {
	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	 ProfessionRepository professionRepository;

	  @Test
	  public void should_find_no_professions_if_repository_is_empty() {
	    Iterable<Profession> professions = professionRepository.findAll();

	    assertThat(professions).isEmpty();
	  }
	  @Test
	  public void should_store_a_profession() {
	    Profession profession = professionRepository.save(new Profession("Instituteur"));

	    assertThat(profession).hasFieldOrPropertyWithValue("professionname", "Instituteur");
	    }
	  @Test
	  public void should_find_all_professions() {
	    Profession p1 = new Profession("Medecin");
	    entityManager.persist(p1);

	    Profession p2 = new Profession("Infirmier");
	    entityManager.persist(p2);

	    Profession p3 = new Profession("Secretaire");
	    entityManager.persist(p3);

	    Iterable<Profession> professions = professionRepository.findAll();

	    assertThat(professions).hasSize(3).contains(p1, p2, p3);
	  }
	  @Test
	  public void should_find_profession_by_id() {
		   Profession p1 = new Profession("Medecin");
		    entityManager.persist(p1);

		    Profession p2 = new Profession("Infirmier");
		    entityManager.persist(p2);

	   Profession foundProfession= professionRepository.findById(p2.getId()).get();

	    assertThat(foundProfession).isEqualTo(p2);
	  }

	  

	  @Test
	  public void should_update_profession_by_id() {
		  Profession p1 = new Profession("Medecin");
		    entityManager.persist(p1);

		    Profession p2 = new Profession("Infirmier");
		    entityManager.persist(p2);

	    Profession updatedPro = new Profession("Medecin Generaliste");

	    Profession pro = professionRepository.findById(p1.getId()).get();
	    pro.setProfessionname(updatedPro.getProfessionname());
	   
	    professionRepository.save(pro);

	    Profession checkPro = professionRepository.findById(p1.getId()).get();
	    
	    assertThat(checkPro.getId()).isEqualTo(p1.getId());
	    assertThat(checkPro.getProfessionname()).isEqualTo(updatedPro.getProfessionname());
	  }

	  @Test
	  public void should_delete_profession_by_id() {
		  Profession p1 = new Profession("Medecin");
		    entityManager.persist(p1);

		    Profession p2 = new Profession("Infirmier");
		    entityManager.persist(p2);
		    
		    Profession p3 = new Profession("Policier");
		    entityManager.persist(p3);
	   professionRepository.deleteById(p2.getId());

	    Iterable<Profession> professions = professionRepository.findAll();

	    assertThat(professions).hasSize(2).contains(p1, p3);
	  }

	  @Test
	  public void should_delete_all_professions() {
	    entityManager.persist(new Profession("Medecin"));
	    entityManager.persist(new Profession("Infirmier"));

	    professionRepository.deleteAll();

	    assertThat(professionRepository.findAll()).isEmpty();
	  }
}
