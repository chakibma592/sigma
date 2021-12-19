package com.optimgov.spring.elearning;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.optimgov.spring.elearning.models.ERole;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.models.Role;
import com.optimgov.spring.elearning.models.StudiesLevel;
import com.optimgov.spring.elearning.models.UploadedFile;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.repository.ProfessionRepository;
import com.optimgov.spring.elearning.repository.RoleRepository;
import com.optimgov.spring.elearning.repository.StudiesLevelRepository;
import com.optimgov.spring.elearning.repository.UploadedFileRepository;
import com.optimgov.spring.elearning.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application2.properties")
public class UserJpaTest {
	 @Autowired
	 private TestEntityManager entityManager;
	 @Autowired
	 UserRepository userRepository;
	 @Autowired
	 UploadedFileRepository fileRepository;
	 @Autowired
	 ProfessionRepository professionRepository;
	 @Autowired
	 StudiesLevelRepository levelRepository;
	 @Autowired
	 RoleRepository roleRepository;
	 
	  @Test
	  public void should_find_no_users_if_repository_is_empty() {
	    Iterable<User> users = userRepository.findAll();
	    assertThat(users).isEmpty();
	  }
	  @Test
	  public void should_store_a_user() throws ParseException {
		UploadedFile file=fileRepository.save(new UploadedFile("uploads/avatar.png"));
		Profession profession=professionRepository.save(new Profession("Etudiant"));
		StudiesLevel level= levelRepository.save(new StudiesLevel("Debutant"));
		roleRepository.save(new Role(ERole.ROLE_USER));
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = format.parse("1983-06-20");
		User user = userRepository.save(new User("chakib", "maoura", "chakib", "chakibmaoura@gmail.com", "chakibmaoura@1983", date, true, date, roles, profession, level, file));
	    assertThat(user).hasFieldOrPropertyWithValue("username", "chakib");
	    assertThat(user).hasFieldOrPropertyWithValue("email", "chakibmaoura@gmail.com");
	    assertThat(user).hasFieldOrPropertyWithValue("password", "chakibmaoura@1983");
	    assertThat(user).hasFieldOrPropertyWithValue("firstname", "maoura");
	    assertThat(user).hasFieldOrPropertyWithValue("lastname", "chakib");
	    assertThat(user).hasFieldOrPropertyWithValue("birthday", date);
	    assertThat(user).hasFieldOrPropertyWithValue("created_at", date);
	    assertThat(user).hasFieldOrPropertyWithValue("studieslevel", level);
	    assertThat(user).hasFieldOrPropertyWithValue("profession", profession);
	    assertThat(user).hasFieldOrPropertyWithValue("file", file);
	    assertThat(user).hasFieldOrPropertyWithValue("activate", true);
	    }
	  @Test
	  public void should_find_all_users() throws ParseException {
		  	UploadedFile file=fileRepository.save(new UploadedFile("uploads/avatar.png"));
			Profession profession=professionRepository.save(new Profession("Etudiant"));
			StudiesLevel level= levelRepository.save(new StudiesLevel("Debutant"));
			roleRepository.save(new Role(ERole.ROLE_USER));
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = format.parse("1983-06-20");
			User u1 = new User("chakib", "maoura", "chakib", "chakibmaoura@gmail.com", "chakibmaoura@1983", date, true, date, roles, profession, level, file);
		    
	    entityManager.persist(u1);
	   file=fileRepository.save(new UploadedFile("uploads/avatar.png"));
	    profession=professionRepository.save(new Profession("Etudiant"));
		level= levelRepository.save(new StudiesLevel("Debutant"));
		
		userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		date = format.parse("1983-06-20");
		User u2 = new User("riad", "riadmaoura", "maoura", "riadmaoura1@gmail.com", "chakibmaoura@1983", date, true, date, roles, profession, level, file);
	    entityManager.persist(u2);
	    Iterable<User> users = userRepository.findAll();
	    assertThat(users).hasSize(2).contains(u1,u2);
	  }
	  @Test
	  public void should_find_user_by_id() throws ParseException {
			UploadedFile file=fileRepository.save(new UploadedFile("uploads/avatar.png"));
			Profession profession=professionRepository.save(new Profession("Etudiant"));
			StudiesLevel level= levelRepository.save(new StudiesLevel("Debutant"));
			roleRepository.save(new Role(ERole.ROLE_USER));
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = format.parse("1983-06-20");
			User u1 = new User("chakib", "maoura", "chakib", "chakibmaoura@gmail.com", "chakibmaoura@1983", date, true, date, roles, profession, level, file);
		    
	    entityManager.persist(u1);
	   file=fileRepository.save(new UploadedFile("uploads/avatar.png"));
	    profession=professionRepository.save(new Profession("Etudiant"));
		level= levelRepository.save(new StudiesLevel("Debutant"));
		
		userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		date = format.parse("1983-06-20");
		User u2 = new User("riad", "riadmaoura", "maoura", "riadmaoura1@gmail.com", "chakibmaoura@1983", date, true, date, roles, profession, level, file);
	    entityManager.persist(u2);

		   User user= userRepository.findById(u2.getId()).get();

	    assertThat(user).isEqualTo(u2);
	  }
	  @Test
	  public void should_update_user_by_id() throws ParseException {
			UploadedFile file=fileRepository.save(new UploadedFile("uploads/avatar.png"));
			Profession profession=professionRepository.save(new Profession("Etudiant"));
			StudiesLevel level= levelRepository.save(new StudiesLevel("Debutant"));
			roleRepository.save(new Role(ERole.ROLE_USER));
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = format.parse("1983-06-20");
			User u1 = new User("chakib", "maoura", "chakib", "chakibmaoura@gmail.com", "chakibmaoura@1983", date, true, date, roles, profession, level, file);
		    
	    entityManager.persist(u1);
	   file=fileRepository.save(new UploadedFile("uploads/avatar.png"));
	    profession=professionRepository.save(new Profession("Etudiant"));
		level= levelRepository.save(new StudiesLevel("Debutant"));
		
		userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		date = format.parse("1983-06-20");
		User u2 = new User("riad", "riadmaoura", "maoura", "riadmaoura1@gmail.com", "chakibmaoura@1983", date, true, date, roles, profession, level, file);
	    entityManager.persist(u2);

		User updateduser = new User();
		updateduser.setFirstname("Maoura");
		updateduser.setLastname("Med riad");
		updateduser.setUsername("med_riad");
		updateduser.setPassword("chakibmaoura@2015");
		updateduser.setBirthday(format.parse("2015-08-24"));
		updateduser.setFile(file);
		updateduser.setProfession(profession);
		updateduser.setActivate(false);
		updateduser.setRoles(roles);
		updateduser.setCreated_at(format.parse("2015-08-24"));
		updateduser.setStudieslevel(level);
		
		

		User user = userRepository.findById(u1.getId()).get();
	    
	   
	    userRepository.save(user);

	    User checkUser = userRepository.findById(u1.getId()).get();	    
	    assertThat(checkUser.getId()).isEqualTo(u1.getId());
	    assertThat(checkUser.getUsername()).isEqualTo(updateduser.getUsername());
	  }
}
