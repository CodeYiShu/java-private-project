package com.codeshu.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author CodeShu
 * @date 2023/6/7 17:25
 */
@Order(2)
@Component
@Aspect
public class OrderAspectAnnotation {
	@Pointcut("execution(* com.codeshu.aspect.test.TestClass.*(..))")
	public void pointcut() {

	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("我是OrderAspectAnnotation -- 前置");
		Object proceed = point.proceed();
		System.out.println("我是OrderAspectAnnotation -- 后置");

		return proceed;
	}
}
