package com.codeshu.springgetbean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Bean的工具类
 */
@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringApplicationContextHolder.context = context;
    }


    public static Object getSpringBean(String beanName) {
        if(StringUtils.isBlank(beanName)) {

        }
        return context==null?null:context.getBean(beanName);
    }

    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }

    public static <T> T getBeanByClass(Class<T> c){
        return context.getBean(c);
    }
}
