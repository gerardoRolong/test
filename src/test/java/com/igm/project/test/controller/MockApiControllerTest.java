package com.igm.project.test.controller;

import com.igm.project.test.api.controller.MockApiController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(MockApiController.class)
public class MockApiControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @Test
  public void testEmulatedApi() throws Exception{
    
    //testing for 2 consecutive calls, the second one should return 429
    mockMvc.perform(get("/mock_api/hit_count"))
        .andExpect(status().isOk())
        .andExpect(content().string("1"));
        
    mockMvc.perform(get("/mock_api/hit_count"))
        .andExpect(status().isTooManyRequests());    
  }
  
}
