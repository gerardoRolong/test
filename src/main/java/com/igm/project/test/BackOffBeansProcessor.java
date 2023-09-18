package com.igm.project.test;

import com.igm.project.test.api.controller.ExternalResourceComunicationController;
import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BackOffBeansProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof ExternalResourceComunicationController) {
      return createProxy(bean);
    }
    return bean;
  }

  private Object createProxy(Object originalBean) {
    Class<?>[] interfaces = originalBean.getClass().getInterfaces();
    Object proxy = Proxy.newProxyInstance(
        originalBean.getClass().getClassLoader(),
        interfaces,
        new ExponentialBackOffHandler(originalBean));
    return proxy;
  }

}
