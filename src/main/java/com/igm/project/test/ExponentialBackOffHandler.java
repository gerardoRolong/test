package com.igm.project.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.web.client.HttpClientErrorException;
import com.igm.project.test.api.annotations.ExponentialBackOff;

public class ExponentialBackOffHandler implements InvocationHandler {
  
  private final Object targetBean;
  
  public ExponentialBackOffHandler(Object targetBean) {
    this.targetBean = targetBean;
  }

  @Override
  public Object invoke(Object targetBean, Method method, Object[] args) throws Throwable {

    if (method.isAnnotationPresent(ExponentialBackOff.class)) {
      int attempts = 5;
      for (int i = 0; i < attempts; i++) {
        try {
          return method.invoke(targetBean, args);
        } catch (Exception ex) {
          if (ex.getCause() instanceof HttpClientErrorException.TooManyRequests) {
            if (i == attempts - 1) {//still failed even after waiting
              throw ex;
            }
            Thread.sleep(1000 * (i + 1));//wait more for each attempt
          } else {
            throw ex;
          }
        }
      }
    }
    return method.invoke(targetBean, args);
  }

}
