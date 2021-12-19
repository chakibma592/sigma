package com.optimgov.spring.elearning.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.models.Topic;
import com.optimgov.spring.elearning.repository.TopicRepository;

@org.springframework.boot.test.context.SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ContextConfiguration
public class TopicControllerTest {
	@Autowired
    private MockMvc mockMvc; 
    @Autowired
    private ObjectMapper objectMapper;    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    private static Topic topicModel;
    private String topicId;
    private static String token;
    @BeforeAll
    private void setUp() throws Exception{
       topicModel = new Topic("Informatique");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/authentication/signin")
                .content("{\"username\": \"chakib1\", \"password\":\"chakibriadmaoura2415\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
           token=JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");  		
    
    }


    @BeforeEach
    private void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }
    @Test
    @Order(1)
    void createTopic() throws Exception {
           MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/topics/form")
                .content(objectMapper.writeValueAsString(topicModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+token)
        ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
          
       topicId = ""+JsonPath.read(result.getResponse().getContentAsString(),"$.id");
       topicModel.setId(Long.parseLong(topicId));
       Assert.assertTrue(topicRepository.findById(Long.parseLong(topicId)).isPresent());
    }
    @Test
    @Order(2)
    void createTopic_2() throws Exception {
    	Topic t= new Topic();
           MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/topics/form")
                .content(objectMapper.writeValueAsString(t))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+token)
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();
          
    }
    @Test
    @Order(3)
    void updateTopic() throws Exception {
        Topic topicRequestModelUpdate = new Topic();
        BeanUtils.copyProperties(topicModel,topicRequestModelUpdate);
        topicRequestModelUpdate.setTopicname("Langages");
    
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/topics/update/"+topicId)
                .content(objectMapper.writeValueAsString(topicRequestModelUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    String jsonResponse = JsonPath.read(result.getResponse().getContentAsString(), "$.topicname");
    Assert.assertEquals(jsonResponse, "Langages");
    }
    @Test
    @Order(4)
    void updateTopic_1() throws Exception {
        Topic topicRequestModelUpdate = new Topic();
        BeanUtils.copyProperties(topicModel,topicRequestModelUpdate);
        topicRequestModelUpdate.setTopicname("Langages");
        topicRequestModelUpdate.setId(Long.parseLong("2344"));
    
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/topics/update/"+topicRequestModelUpdate.getId())
                .content(objectMapper.writeValueAsString(topicRequestModelUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token)
        ).andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
    
    }
    @Test
    @Order(5)
    void deleteTopic() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/topics/delete/"+topicId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token)
        ).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
        Assert.assertFalse(topicRepository.findById(Long.parseLong(topicId)).isPresent());
    }
    @Test
    @Order(6)
    void deleteTopic_2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/topics/delete/"+topicId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+token)
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();
    }
    
}
