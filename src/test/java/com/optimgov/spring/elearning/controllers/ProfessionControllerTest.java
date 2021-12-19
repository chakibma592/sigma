package com.optimgov.spring.elearning.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
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
import com.optimgov.spring.elearning.repository.ProfessionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@org.springframework.boot.test.context.SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ContextConfiguration
public class ProfessionControllerTest {
	@Autowired
    private MockMvc mockMvc; 
    @Autowired
    private ObjectMapper objectMapper;    
    @Autowired
    private ProfessionRepository professionRepository;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    private static Profession professionModel;
    private String professionId;
    private static String token;
    @BeforeAll
    private void setUp() throws Exception{
       professionModel = new Profession("Professeur");

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
    void createProfession() throws Exception {
           MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/professions/form")
                .content(objectMapper.writeValueAsString(professionModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+token)
        ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
          
       professionId = ""+JsonPath.read(result.getResponse().getContentAsString(),"$.id");
       professionModel.setId(Long.parseLong(professionId));
       Assert.assertTrue(professionRepository.findById(Long.parseLong(professionId)).isPresent());
    }
   @Test
   @Order(2)
   void updateProfession() throws Exception {
       Profession professionRequestModelUpdate = new Profession();
       BeanUtils.copyProperties(professionModel,professionRequestModelUpdate);
       professionRequestModelUpdate.setProfessionname("Etudiant");
   
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/professions/update/"+professionId)
               .content(objectMapper.writeValueAsString(professionRequestModelUpdate))
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .header("Authorization","Bearer "+token)
       ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
   String jsonResponse = JsonPath.read(result.getResponse().getContentAsString(), "$.professionname");
   Assert.assertEquals(jsonResponse, "Etudiant");
   }
   
   @Test
   @Order(3)
   void getProfessionsList() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/api/professions/list")
                .header("Authorization","Bearer "+token))
               .andExpect(MockMvcResultMatchers.status().isOk());
   }
   @Test
   @Order(4)
   void deleteProfession() throws Exception {
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/professions/delete/"+professionId)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .header("Authorization","Bearer "+token)
       ).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
       Assert.assertFalse(professionRepository.findById(Long.parseLong(professionId)).isPresent());
   }
   
   @Test
   @Order(5)
   void createProfession_2() throws Exception {
   Profession p= new Profession();
          MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/professions/form")
               .content(objectMapper.writeValueAsString(p))
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .header("Authorization", "Bearer "+token)
       ).andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();
         
   }
   @Test
   @Order(6)
   void updateProfession_1() throws Exception {
       Profession professionRequestModelUpdate = new Profession();
       BeanUtils.copyProperties(professionModel,professionRequestModelUpdate);
       professionRequestModelUpdate.setProfessionname("test");
       professionRequestModelUpdate.setId(Long.parseLong("2344"));
   
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/professions/update/"+professionRequestModelUpdate.getId())
               .content(objectMapper.writeValueAsString(professionRequestModelUpdate))
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .header("Authorization","Bearer "+token)
       ).andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
   
   }
   @Test
   @Order(7)
   void deleteProfession_2() throws Exception {
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/professions/delete/"+professionId)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .header("Authorization","Bearer "+token)
       ).andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();
   }
   @Test
   @Order(8)
   void getProfessionsListEmpty() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/api/professions/list")
                .header("Authorization","Bearer "+token))
               .andExpect(MockMvcResultMatchers.status().isNoContent());
   }
 
}
