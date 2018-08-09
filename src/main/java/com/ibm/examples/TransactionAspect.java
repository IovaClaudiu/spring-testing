package com.ibm.examples;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Aspect
public class TransactionAspect {

	@Autowired
	private PlatformTransactionManager manager;

	private TransactionTemplate template;

	@Around(value = "@annotation(MyTransactional)")
	public Object startTransaction(ProceedingJoinPoint pjp) {
		initTransactionTemplate();
		System.out.println("Starting the AOP on every method annnotated with MyTransactional");
		Object obj = createTransaction(pjp);
		boolean value = getAnnotationValue(((MethodSignature) pjp.getSignature()).getMethod());
		System.out.println(
				"Done the AOP on every method annnotated with MyTransactional, the annotation value was: " + value);
		return obj;
	}

	private Object createTransaction(ProceedingJoinPoint pjp) {
		Object[] obj = new Object[1];
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					obj[0] = pjp.proceed();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		return obj[0];
	}

	private void initTransactionTemplate() {
		if (template == null)
			template = new TransactionTemplate(manager);
	}

	public boolean getAnnotationValue(Method method) {
		return method.getAnnotation(MyTransactional.class).readOnly();
	}
}
