package com.igm.project.test.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.igm.project.test.api.service.HtmlGenerationService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/")
public class HtmlGenerationController {
  
  private final HtmlGenerationService htmlGenerationService;

  public HtmlGenerationController(HtmlGenerationService htmlGenerationService) {
    this.htmlGenerationService = htmlGenerationService;
  }
  
  @GetMapping(value="/view")
  public ResponseEntity<String> getMethodName() {
      return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlGenerationService.generateCompleteHtml());
  }  
  
}
