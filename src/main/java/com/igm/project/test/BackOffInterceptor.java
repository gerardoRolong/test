package com.igm.project.test;

import com.igm.project.test.api.annotations.ExponentialBackOff;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BackOffInterceptor implements HandlerInterceptor {
  
  // @Override
  // public boolean afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  //     return true;
  // }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    if(handler instanceof HandlerMethod){
      Method annotatedMethod = ((HandlerMethod) handler).getMethod();
      Object controllerMethod = ((HandlerMethod) handler).getBean();
      
      if(annotatedMethod.isAnnotationPresent(ExponentialBackOff.class)){
        int attempts = 5;
        for (int i = 0; i < attempts; i++) {
          try {
            Object current_hits = annotatedMethod.invoke(controllerMethod, new Integer[0]);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("" + current_hits);
            return false;
          } catch (Exception exec) {
            if (exec.getCause() instanceof HttpClientErrorException.TooManyRequests) {
              if (i == attempts - 1) {// still failed even after waiting
                throw exec;
              }
              Thread.sleep(1000 * (i + 1));// wait more for each attempt
            }
          }
        }
      }
    }
    return true;
  }
  
  // @Override
  // public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
  //    throws Exception {
    
  //   if(handler instanceof HandlerMethod){
  //     Method annotatedMethod = ((HandlerMethod) handler).getMethod();
  //     Object controllerMethod = ((HandlerMethod) handler).getBean();
      
  //     if(annotatedMethod.isAnnotationPresent(ExponentialBackOff.class) && ex != null){
  //       int attempts = 2;
  //       response.reset();
  //       try {
  //         for (int i = 0; i < attempts; i++) {
  //           int current_hits = (Integer)annotatedMethod.invoke(controllerMethod, new Integer[0]);
  //           response.setStatus(HttpServletResponse.SC_OK);
  //           response.setContentType("text/html");
  //           response.setCharacterEncoding("UTF-8");
  //           response.getWriter().write("" + current_hits);
  //           ex = null;
  //           break;
  //             // return false;
  //           //   else {
  //           //     throw exec;
  //           //   }
  //         }
  //       } catch (Exception exec) {
  //         System.out.println("exec");
  //         System.out.println(exec.getMessage());
  //         // if (exec.getCause() instanceof HttpClientErrorException.TooManyRequests) {
  //         //   if (i == attempts - 1) {// still failed even after waiting
  //         //   //   throw exec;
  //         //     break;
  //         //   }
  //         //   Thread.sleep(1000 * (i + 1));// wait more for each attempt
  //         // } 
  //       }
  //     }
  //   }
  // }
}
