package com.igm.project.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadPoolConfiguration {
  
  private ExecutorService executorService;
  
  @Bean
  public ExecutorService getExecutorService() {
    executorService = Executors.newFixedThreadPool(8);
    return executorService;
  }
  
}