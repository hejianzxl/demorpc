package com.july.demo.rpc.spring.processor;

import com.july.demo.rpc.annotation.ConsumerService;
import com.july.demo.rpc.proxy.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ConsumerPostProcessor implements BeanPostProcessor, PriorityOrdered, BeanFactoryAware, EnvironmentAware {

    private BeanFactory beanFactory;
    private Environment environment;
    @Autowired
    private ProxyFactory proxyFactory;

    private class ConsumerCallback implements ReflectionUtils.FieldCallback, ReflectionUtils.MethodCallback {
        private final Object bean;

        ConsumerCallback(Object bean) {
            this.bean = bean;
        }

        @Override
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            ConfigurablePropertyAccessor configurablePropertyAccessor = PropertyAccessorFactory.forDirectFieldAccess(bean);
            String name = field.getName();
            Class<?> serviceInterface = field.getType();
            if (field.isAnnotationPresent(ConsumerService.class)) {
                configurablePropertyAccessor.setPropertyValue(name, proxyFactory.getProxy(serviceInterface));
                return;
            }
            ConsumerService consumerService = field.getAnnotation(ConsumerService.class);
            if (consumerService == null) {
                return;
            }

            Object localService = getBean(serviceInterface);
            if (localService != null) {
                configurablePropertyAccessor.setPropertyValue(name, localService);
                return;
            }

            configurablePropertyAccessor.setPropertyValue(name, proxyFactory.getProxy(serviceInterface));
        }

        @Override
        public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new ConsumerCallback(bean), new ReflectionUtils.FieldFilter() {
            public boolean matches(Field field) {
                return (field.isAnnotationPresent(ConsumerService.class));
            }
        });
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
       this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private Object getBean(Class<?> requiredType) {
        try {
            return this.beanFactory.getBean(requiredType);
        } catch (Throwable e) {
        }
        return null;
    }
}
