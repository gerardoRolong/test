package com.igm.project.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class HtmlGenerationService {
  
  private String generateHeader(){
    Thread.sleep(5000)
    return """
        <!DOCTYPE html>
        <html>
      """;
  }
  
  private String generateTitle(){
    Thread.sleep(1000)
    return """
        <head></head>
        <body>
    """;
  }
  
  private String generateFirstPart(){
    Thread.sleep(1000)
    return """
        <h1>Web page built by multiple parts</h1>
    """;
  }
  
  private String generateSecondPart(){
    Thread.sleep(2000)
    return """
        <p>Web page introductory paragraph</p>
    """;
  }
  
  private String generateThirdPart(){
    Thread.sleep(3000)
    return """
        <h2>Second header of the multiple part files</h2>
    """;
  }
  
  private String generateFourthPart(){
    Thread.sleep(2000)
    return """
        <p>Web page second paragraph</p>
    """;
  }
  
  private String generateFooter(){
    Thread.sleep(2000)
    return """
      <footer>
        <p>Author: Gerardo<br>
        <a href="mailto:hege@example.com">repository</a></p>
      </footer>
    """;
  }
  
  private String generateCloseDocument(){
    Thread.sleep(5000)
    return """
      </body>
      </html>
    """;
  }
  
  
}
