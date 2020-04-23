package com.semi.update.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// #17

@Component  // 스프링 빈으로 인식되게 하는 어노테이션
@Aspect		// Aop :  pointCut + Advice(CCC)
public class LogAop {
	private static final Logger logger = LoggerFactory.getLogger(LogAop.class);
	
	//매서드 실행 전체 앞, 뒤로 특정한 기능을 실행 하게 해주는 강력한 타입의 Advice >> 리턴타입이 Object여야함
	// Pointcut을 지정하는 문법 (AspectJ 언어 문법을 사용한다)
	// " * " : 모든 값을 표현합니다.
	// " .. " : 0개 이상을 의미합니다
	@Around(value = "execution(* com.semi.update..*Controller.*(..))"
			+ " or execution(* com.semi.update..Biz..*Impl.*(..))"
			+ " or execution(* com.semi.update..Dao..*Impl.*(..))")	
	public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
							//  @Around타입의 Advice메서드의 파라미터로 사용되는 인터페이스(JoinPoint의 하위 인테페이스)
		long start = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
							// 다음 Advice를 실행하거나, 실제 target객체의 메서드를 실행하는 기능을 가진 메서드
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
							// getSignature() : 실행하는 대상 객체의 메서드에 대한 정보를 알고 싶을 때 사용한다.
		String name = "";
		
		if(type.contains("Controller")) {
			name = "Controller : ";
		} else if (type.contains("Biz")) {  //Service
			name = "Biz : ";
		} else if (type.contains("Dao")) {  // DAO
			name = "dao : ";
		}	
		
		long end = System.currentTimeMillis();
		
		logger.info("----------------------------------[LogAop] START----------------------------------");
		logger.info(name + type + "." + proceedingJoinPoint.getSignature().getName()+ "()");
		logger.info("Running Time : " + (end - start));
		logger.info("-----------------------------------[LogAop] END-----------------------------------");
		
		return result;
	}
	
	/*
	※ @After : After Advice를 구현

	  	- 대상 객체의 메서드를 실행하는 도중에 예외가 발생했는지의 여부와 상관없이 메서드 실행 후 공통 기능을 실행(try~catch~finally의 finally 블록과 비슷) 
		- 대상 객체 및 호출되는 메서드에 대한 정보나 전달되는 파라미터에 대한 정보가 필요한 경우 org.aspectj.lang.JoinPoint를 파라미터로 명


	※ @Around : Around Advice를 구현

	    - 대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행
	    - Around Advice를 구현한 메서드는 org.aspectj.lang.ProceedingJoinPoint를 반드시 첫 번째 파리미터로 지정해야 함 -> 그렇지 않을 경우 예외 발생 

	*/
	
	
}
