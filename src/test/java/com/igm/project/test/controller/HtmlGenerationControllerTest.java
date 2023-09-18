package com.igm.project.test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.igm.project.test.api.controller.HtmlGenerationController;
import com.igm.project.test.api.service.HtmlGenerationService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(HtmlGenerationController.class)
public class HtmlGenerationControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  HtmlGenerationService htmlGenerationService;
  
  @Test
  public void testGetGeneratedHtml() throws Exception{
    String html = """
      <!DOCTYPE html>
      <html>
      </html>
    """;
    when(htmlGenerationService.generateCompleteHtml()).thenReturn(html);    
    mockMvc.perform(get("/html_generation/view"))
        .andExpect(status().isOk())
        .andExpect(content().string(not(emptyOrNullString())));
  }
  
}
