package com.igm.project.test.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.igm.project.test.api.annotations.ExponentialBackOff;

/**
 * Controller to make calls to external(emulated) APIs 
 */
@RestController
@RequestMapping("/external_api")
public class ExternalResourceComunicationController {
  
  private RestTemplate restTemplate;
  
  public ExternalResourceComunicationController(RestTemplate restTemplate){
    this.restTemplate = restTemplate;
  }
  
  /**
   * Method annotated with a custom annotation to handle an exponential back off
   * with http calls that return a 429 error once in a while
   */
  @ExponentialBackOff
  @GetMapping(value="/test_back_off")
  public Integer getHitCount() {
    Integer hitCount = 0;
    String url = "http://localhost:8080/mock_api/hit_count";

    ResponseEntity<Integer> response
        = restTemplate.getForEntity(url, Integer.class);
    
    hitCount = response.getBody();
    return hitCount;
  }
}
