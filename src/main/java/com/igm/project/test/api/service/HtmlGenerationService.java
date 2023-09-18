package com.igm.project.test.api.service;

import com.igm.project.test.ThreadPoolConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HtmlGenerationService {
  
  @Autowired
  private ThreadPoolConfiguration threadPoolConfiguration;
  
  /**
   * Generates a html file using multiple threads, each gets executed in parallel
   * the main thread waits for all tasks to finish and then generates the complete file
   */
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
      for(Future<String> future : futureHtmlParts){
        resp += future.get().toString() + "\n";
      }
    } catch (Exception e) {
      log.debug("an error during multi thread html generation occurred:");
      e.printStackTrace();
      return "error";//returning this for now, should use error page + throw exception
    }    
    return resp;
  }
  
  private String generateHeader() throws InterruptedException{
    Thread.sleep(5000);
    System.out.println("thread1");
    return """
        <!DOCTYPE html>
        <html>
      """;
  }
  
  private String generateTitle() throws InterruptedException{
    Thread.sleep(1000);
    System.out.println("thread2");
    return """
        <head></head>
        <body>
    """;
  }
  
  private String generateFirstPart()  throws InterruptedException{
    Thread.sleep(1000);
    System.out.println("thread3");
    return """
        <h1>Web page built by multiple parts</h1>
    """;
  }
  
  private String generateSecondPart()  throws InterruptedException{
    Thread.sleep(2000);
    System.out.println("thread4");
    return """
        <p>Web page introductory paragraph</p>
    """;
  }
  
  private String generateThirdPart()  throws InterruptedException{
    Thread.sleep(3000);
    System.out.println("thread5");
    return """
        <h2>Second header of the multiple part files</h2>
    """;
  }
  
  private String generateFourthPart()  throws InterruptedException{
    Thread.sleep(2000);
    System.out.println("thread6");
    return """
        <p>Web page second paragraph</p>
    """;
  }
  
  private String generateFooter()  throws InterruptedException{
    Thread.sleep(2000);
    System.out.println("thread7");
    return """
      <footer>
        <p>Author: Gerardo<br>
        <a href="https://github.com/gerardoRolong/test">repository</a></p>
      </footer>
    """;
  }
  
  private String generateCloseDocument()  throws InterruptedException{
    Thread.sleep(5000);
    System.out.println("thread8");
    return """
      </body>
      </html>
    """;
  }
  
  
}
