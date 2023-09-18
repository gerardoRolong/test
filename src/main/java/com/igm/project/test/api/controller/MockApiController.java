package com.igm.project.test.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to emulate an external api that frequently return a 429 too many requests error
 */
@RestController
@RequestMapping("/mock_api")
public class MockApiController {
  
  private static int hitCount = 0;
  
  @GetMapping(value = "/hit_count")
  public ResponseEntity<Integer> getHitCOunt(){
    hitCount += 1;
    if(hitCount == 2 || hitCount == 3){
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
    if(hitCount == 5){
      hitCount = 1;
    }
    return ResponseEntity.ok().body(hitCount);
  }
}