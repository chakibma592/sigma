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
import com.optimgov.spring.elearning.models.Topic;
import com.optimgov.spring.elearning.repository.TopicRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application2.properties")
public class TopicJpaTest {
	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	 TopicRepository topicRepository;

	  @Test
	  public void should_find_no_topics_if_repository_is_empty() {
	    Iterable<Topic> topics = topicRepository.findAll();

	    assertThat(topics).isEmpty();
	  }
	  @Test
	  public void should_store_a_topic() {
		  Topic topic= topicRepository.save(new Topic("Informatique"));

	    assertThat(topic).hasFieldOrPropertyWithValue("topicname", "Informatique");
	    }
	  @Test
	  public void should_find_all_topics() {
		Topic p1 = new Topic("Informatique");
	    entityManager.persist(p1);

	    Topic p2 = new Topic("Langues");
	    entityManager.persist(p2);

	    Topic p3 = new Topic("Mathematiques");
	    entityManager.persist(p3);

	    Iterable<Topic> topics = topicRepository.findAll();

	    assertThat(topics).hasSize(3).contains(p1, p2, p3);
	  }
	  @Test
	  public void should_find_topic_by_id() {
		  Topic p1 = new Topic("Informatique");
		    entityManager.persist(p1);

		  Topic p2 = new Topic("Mathematiques");
		    entityManager.persist(p2);

		  Topic foundTopic= topicRepository.findById(p2.getId()).get();

	    assertThat(foundTopic).isEqualTo(p2);
	  }

	  

	  @Test
	  public void should_update_topic_by_id() {
		  Topic p1 = new Topic("Informatique");
		    entityManager.persist(p1);

		    Topic p2 = new Topic("Langue");
		    entityManager.persist(p2);

		    Topic updatedTopic = new Topic("Langues");

		    Topic topic = topicRepository.findById(p1.getId()).get();
	    topic.setTopicname(updatedTopic.getTopicname());
	   
	    topicRepository.save(topic);

	    Topic checkTopic = topicRepository.findById(p1.getId()).get();
	    
	    assertThat(checkTopic.getId()).isEqualTo(p1.getId());
	    assertThat(checkTopic.getTopicname()).isEqualTo(updatedTopic.getTopicname());
	  }

	  @Test
	  public void should_delete_topic_by_id() {
		  Topic p1 = new Topic("Informatique");
		    entityManager.persist(p1);

		    Topic p2 = new Topic("Langues");
		    entityManager.persist(p2);

		    Topic p3 = new Topic("Mathematiques");
		    entityManager.persist(p3);
		    topicRepository.deleteById(p2.getId());

	    Iterable<Topic> topics = topicRepository.findAll();

	    assertThat(topics).hasSize(2).contains(p1, p3);
	  }

	  @Test
	  public void should_delete_all_topics() {
	    entityManager.persist(new Profession("Mathematiques"));
	    entityManager.persist(new Profession("Informatique"));

	    topicRepository.deleteAll();

	    assertThat(topicRepository.findAll()).isEmpty();
	  }
}
