package com.codeshu.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author CodeShu
 * @date 2023/7/19 11:26
 */
@Component
public class LifeBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean {
	private DependentObject dependentObject;

	public LifeBean(DependentObject dependentObject) {
		System.out.println("LifeBean 被创建");
		this.dependentObject = dependentObject;
		System.out.println("LifeBean 被依赖注入");
	}

	@PostConstruct
	public void init() {
		System.out.println("LifeBean 的初始化方法 init()");
	}

	@PreDestroy
	public void destory() {
		System.out.println("LifeBean 的销毁方法 destory()");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("LifeBean 的 setBeanName()，传入参数：" + name);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("LifeBean 的 setBeanFactory()，传入参数：" + beanFactory);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("LifeBean 的 setApplicationContext()，传入参数：" + applicationContext);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("LifeBean 的 postProcessBeforeInitialization()");
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("LifeBean 的 afterPropertiesSet()");
	}


	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("LifeBean 的 postProcessAfterInitialization()");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
