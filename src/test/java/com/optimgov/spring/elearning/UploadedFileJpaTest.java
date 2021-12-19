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
import com.optimgov.spring.elearning.models.UploadedFile;
import com.optimgov.spring.elearning.repository.UploadedFileRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application2.properties")
public class UploadedFileJpaTest {
	@Autowired
	  private TestEntityManager entityManager;

	 @Autowired
	 UploadedFileRepository fileRepository;

	  @Test
	  public void should_find_no_file_if_repository_is_empty() {
	    Iterable<UploadedFile> files = fileRepository.findAll();

	    assertThat(files).isEmpty();
	  }
	  @Test
	  public void should_store_a_file() {
		  UploadedFile file = fileRepository.save(new UploadedFile("uploads/img.jpg"));

	    assertThat(file).hasFieldOrPropertyWithValue("fileurl", "uploads/img.jpg");
	    }
	  @Test
	  public void should_find_all_files() {
		UploadedFile p1 = new UploadedFile("uploads/img1.jpg");
	    entityManager.persist(p1);

	    UploadedFile p2 = new UploadedFile("uploads/img2.jpg");
	    entityManager.persist(p2);

	    UploadedFile p3 = new UploadedFile("uploads/img3.jpg");
	    entityManager.persist(p3);

	    Iterable<UploadedFile> files = fileRepository.findAll();

	    assertThat(files).hasSize(3).contains(p1, p2, p3);
	  }
	  @Test
	  public void should_find_file_by_id() {
		  UploadedFile p1 = new UploadedFile("uploads/img1.jpg");
		    entityManager.persist(p1);

		    UploadedFile p2 = new UploadedFile("uploads/img2.jpg");
		    entityManager.persist(p2);

		    UploadedFile foundFile= fileRepository.findById(p2.getId()).get();

	    assertThat(foundFile).isEqualTo(p2);
	  }

	  

	  @Test
	  public void should_update_file_by_id() {
		UploadedFile p1 = new UploadedFile("uploads/img1.jpg");
		    entityManager.persist(p1);

		UploadedFile p2 = new UploadedFile("uploads/img2.jpg");
		    entityManager.persist(p2);

		UploadedFile updatedfile = new UploadedFile("uploads/img3.jpg");

		UploadedFile file = fileRepository.findById(p1.getId()).get();
	    file.setFileurl(updatedfile.getFileurl());
	   
	    fileRepository.save(file);

	    UploadedFile checkFile = fileRepository.findById(p1.getId()).get();
	    
	    assertThat(checkFile.getId()).isEqualTo(p1.getId());
	    assertThat(checkFile.getFileurl()).isEqualTo(updatedfile.getFileurl());
	  }

	  @Test
	  public void should_delete_file_by_id() {
		  UploadedFile p1 = new UploadedFile("uploads/img1.jpg");
		    entityManager.persist(p1);

		    UploadedFile p2 = new UploadedFile("uploads/img2.jpg");
		    entityManager.persist(p2);

		    UploadedFile p3 = new UploadedFile("uploads/img3.jpg");
		    entityManager.persist(p3);
		    fileRepository.deleteById(p2.getId());

	    Iterable<UploadedFile> files =  fileRepository.findAll();

	    assertThat(files).hasSize(2).contains(p1, p3);
	  }

	  @Test
	  public void should_delete_all_files() {
	    entityManager.persist(new UploadedFile("uploads/img1.jpg"));
	    entityManager.persist(new UploadedFile("uploads/img1.jpg"));

	    fileRepository.deleteAll();

	    assertThat(fileRepository.findAll()).isEmpty();
	  }

}
