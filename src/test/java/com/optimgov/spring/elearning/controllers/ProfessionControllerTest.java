package com.optimgov.spring.elearning.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.repository.ProfessionRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;



@org.springframework.boot.test.context.SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
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
        token =result.getResponse().getHeader("Authorization");
        System.err.println(result.getResponse().getContentAsString());
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
                .header("Authorization", token)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        professionId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        Assert.assertTrue(professionRepository.findById(Long.parseLong(professionId)).isPresent());
    }
    
}
