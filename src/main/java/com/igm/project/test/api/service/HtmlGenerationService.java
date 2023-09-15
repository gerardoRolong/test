package com.igm.project.test.api.service;

import com.igm.project.test.ThreadPoolConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HtmlGenerationService {
  
  @Autowired
  private ThreadPoolConfiguration threadPoolConfiguration;
  
  public String generateCompleteHtml(){
    String resp = "";
    ExecutorService executorService = threadPoolConfiguration.getExecutorService();
    
    List<Callable<String>> generatingCallables = List.of(() -> generateHeader(),
        () -> generateTitle(),
        () -> generateFirstPart(),
        () -> generateSecondPart(),
        () -> generateThirdPart(),
        () -> generateFourthPart(),
        () -> generateFooter(),
        () -> generateCloseDocument()
    );
    
    try {
      List<Future<String>> futureHtmlParts = executorService.invokeAll(generatingCallables);
      for(Future future : futureHtmlParts){
        resp += future.get().toString() + "\n";
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return "";
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return resp;
  }
  
  private String generateHeader() throws InterruptedException{
    Thread.sleep(5000);
    return """
        <!DOCTYPE html>
        <html>
      """;
  }
  
  private String generateTitle() throws InterruptedException{
    Thread.sleep(1000);
    return """
        <head></head>
        <body>
    """;
  }
  
  private String generateFirstPart()  throws InterruptedException{
    Thread.sleep(1000);
    return """
        <h1>Web page built by multiple parts</h1>
    """;
  }
  
  private String generateSecondPart()  throws InterruptedException{
    Thread.sleep(2000);
    return """
        <p>Web page introductory paragraph</p>
    """;
  }
  
  private String generateThirdPart()  throws InterruptedException{
    Thread.sleep(3000);
    return """
        <h2>Second header of the multiple part files</h2>
    """;
  }
  
  private String generateFourthPart()  throws InterruptedException{
    Thread.sleep(2000);
    return """
        <p>Web page second paragraph</p>
    """;
  }
  
  private String generateFooter()  throws InterruptedException{
    Thread.sleep(2000);
    return """
      <footer>
        <p>Author: Gerardo<br>
        <a href="mailto:hege@example.com">repository</a></p>
      </footer>
    """;
  }
  
  private String generateCloseDocument()  throws InterruptedException{
    Thread.sleep(5000);
    return """
      </body>
      </html>
    """;
  }
  
  
}
