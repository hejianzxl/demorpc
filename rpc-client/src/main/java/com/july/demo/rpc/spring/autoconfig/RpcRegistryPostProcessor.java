package com.july.demo.rpc.spring.autoconfig;

import com.july.demo.rpc.proxy.ProxyFactory;
import com.july.demo.rpc.spring.processor.ConsumerPostProcessor;
import com.july.demo.rpc.spring.processor.ProviderPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

public class RpcRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    public static final String BEAN_NAME = "";

    private Class className;

    private RpcRegistryPostProcessor.BeanDefinitionUtil beanDefinitionUtil;

    public RpcRegistryPostProcessor() {
      this.beanDefinitionUtil = (name, target, beanDefinitionRegistry)->{
          RootBeanDefinition beanDefinition = new RootBeanDefinition(target);
          beanDefinitionRegistry.registerBeanDefinition(name, beanDefinition);
          return beanDefinition.getPropertyValues();
        };
    }


    @FunctionalInterface
    static interface BeanDefinitionUtil {
        public MutablePropertyValues doParse(String name, Class target, BeanDefinitionRegistry registry);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition definition = new RootBeanDefinition(className);
        registry.registerBeanDefinition(BEAN_NAME, definition);

        ProxyFactory proxyFactory = null;

        beanDefinitionUtil.doParse("com.july.demo.rpc.spring.processor.ConsumerPostProcessor", ConsumerPostProcessor.class, registry);

        beanDefinitionUtil.doParse("com.july.demo.rpc.spring.processor.ProviderPostProcessor", ProviderPostProcessor.class, registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
