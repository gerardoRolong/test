package com.igm.project.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.igm.project.test.ThreadPoolConfiguration;
import com.igm.project.test.api.service.HtmlGenerationService;

@ExtendWith(MockitoExtension.class)
public class HtmlGenerationServiceTest {
  
  @InjectMocks
  HtmlGenerationService htmlGenerationService;
  
  @Mock
  ThreadPoolConfiguration threadPoolConfiguration;
  
  @Mock
  ExecutorService executorService;
  
  @Mock
  Future<Object> part1;
  
  @Mock
  Future<Object> partN;
  
  @Test
  public void generateCompleteHtml() throws InterruptedException, ExecutionException{
    
    when(part1.get()).thenReturn("<!DOCTYPE html>");
    when(partN.get()).thenReturn("</html>");
    
    when(threadPoolConfiguration.getExecutorService()).thenReturn(executorService);
    when(executorService.invokeAll(anyList())).thenReturn(List.of(part1, partN));
    
    String resp = htmlGenerationService.generateCompleteHtml();
    verify(executorService, times(1)).invokeAll(anyList());
    
    String expectedHtml = """
    <!DOCTYPE html>
    </html>
    """;
    
    assertEquals(expectedHtml, resp);
  }
  
}
