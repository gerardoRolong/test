package com.igm.project.test.controller;

import com.igm.project.test.api.controller.ExternalResourceComunicationController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ExternalResourceComunicationController.class)
public class ExternalResourceComunicationControllerTest {
  
  @MockBean
  RestTemplate restTemplate;
  
  @Autowired
  private MockMvc mockMvc;
  
  @Test
  public void getHitCountTest() throws Exception{
    
    ResponseEntity<Integer> response = new ResponseEntity<Integer>(1, HttpStatus.OK);
    when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenReturn(response);
    
    mockMvc.perform(get("/external_api/test_back_off"))
        .andExpect(status().isOk())
        .andExpect(content().string(not(emptyOrNullString())));
    
  }
  
}
