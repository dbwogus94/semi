package com.semi.update.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// #17

@Component  // 스프링 빈으로 인식되게 하는 어노테이션
@Aspect		// Aop :  pointCut + Advice(CCC)
public class LogAop {
	private static final Logger logger = LoggerFactory.getLogger(LogAop.class);
	
	//매서드 실행 전체 앞, 뒤로 특정한 기능을 실행 하게 해주는 강력한 타입의 Advice >> [@Around 사용 조건 1 : 리턴타입이 Object여야함]
	// Pointcut을 지정하는 문법 (AspectJ 언어 문법을 사용한다)
	// " * " : 모든 값을 표현합니다.
	// " .. " : 0개 이상을 의미합니다
	@Around(value = "execution(* com.semi.update.*.*Controller.*(..))" + " or execution(* com.semi.update..*BizImpl.*(..))" + " or execution(* com.semi.update..*DaoImpl.*(..))")		
	public Object logRes(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs()); // 타겟 실행
		
		String target = proceedingJoinPoint.getSignature().getDeclaringTypeName(); // 실행 대상 객체
		
		String name = getTargetType(target);
		
		if(name.equals("Controller")) {
			return result;
		}
		
		logger.info("------------------------- [LogAop : Around] START -------------------------------------------------------------------------------------------------");
		logger.info("[AOP] " + name + " : " + target + "." + proceedingJoinPoint.getSignature().getName()+ "()");
		logger.info("[AOP] 실행 후 리턴 값 => " + result);
		logger.info("------------------------- [LogAop : Around] END ---------------------------------------------------------------------------------------------------\n");
		
		return result;	// 타겟을 리턴해야함
	}
	/*
	  	컨트롤러는 ajax(비동기)만 결과 출력하게 만들것
	   	Biz는 비즈니스 로직 분리하면 나오게 할 것
	   	dao는 리턴값이 int형일 경우만 나오게 할 것 
	 */
	
	
	//@Around(value = "execution(* com.semi.update.*.*Controller.*(..))"
	//		+ " or execution(* com.semi.update..*BizImpl.*(..))" 		
	//		+ " or execution(* com.semi.update..*DaoImpl.*(..))")		
	public Object logRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
							//  [@Around 사용 조건 2 : ProceedingJoinPoint 파라미터로 사용한다.]
							
							//  @Around타입의 Advice메서드의 파라미터로 사용되는 인터페이스(JoinPoint의 하위 인테페이스)
							//  ProceedingJoinPoint의 proceed()는 특이하게 Exception보다 상위인 Throwable를 throws 합니다.
		long start = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
							// 다음 Advice를 실행하거나, 실제 target객체의 메서드를 실행하는 기능을 가진 메서드
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
							// getSignature() : 실행하는 대상 객체의 메서드에 대한 정보를 알고 싶을 때 사용한다.
		String name = getTargetType(type);
		
		long end = System.currentTimeMillis();
		
		logger.info("------------------------- [LogAop : Around] START -------------------------------------------------------------------------------------------------");
		logger.info(name + type + "." + proceedingJoinPoint.getSignature().getName()+ "()");
		logger.info("Running Time : " + (end - start));
		logger.info("------------------------- [LogAop : Around] END ---------------------------------------------------------------------------------------------------\n");
		
		return result;
	}
	
	/*
	※ @After : After Advice를 구현

	  	- 대상 객체의 메서드를 실행하는 도중에 예외가 발생했는지의 여부와 상관없이 메서드 실행 후 공통 기능을 실행(try~catch~finally의 finally 블록과 비슷) 
		- 대상 객체 및 호출되는 메서드에 대한 정보나 전달되는 파라미터에 대한 정보가 필요한 경우 org.aspectj.lang.JoinPoint를 파라미터로 명


	※ @Around : Around Advice를 구현

	    - 대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행
	    - Around Advice를 구현한 메서드는 org.aspectj.lang.ProceedingJoinPoint를 반드시 첫 번째 파리미터로 지정해야 함 -> 그렇지 않을 경우 예외 발생
	    - ProceedingJoinPoint.proceed() : 
	    	다음 Advice를 실행하거나, 실제 target객체의 메소드를 실행하는 기능이다.
		- @Around를 사용하는 경우 해당 메소드의 리턴타입은 Object로 선언해야 한다.
	*/
	
	
	@Before("execution(* com.semi.update.*.*Controller.*(..))" + " or execution(* com.semi.update..*BizImpl.*(..))" + " or execution(* com.semi.update..*DaoImpl.*(..))")
	public void logBefore(JoinPoint jp) {
		String methodName = getMethodName(jp.getSignature());
		String name = getTargetType(methodName);
		
		logger.info("------------------------- [LogAop : Before] START -----------------------------------------------------------------------------------------------");
		logger.info("[AOP] " + name +" 실행  : "+ methodName);
		logger.info("[AOP] 파라미터 배열 : " +  Arrays.deepToString(jp.getArgs()));
		//logger.info("[AOP] Advice의 타입 : " +  jp.getKind());
		//logger.info("[AOP] target 객체 :" + jp.getTarget().toString());
		//logger.info("[AOP] Advice를 행하는 객체 : " + jp.getThis().toString());
		logger.info("------------------------- [LogAop : Before] END -------------------------------------------------------------------------------------------------\n");
	}
	/*
	  # JoinPoint의 메서드
	  
	  	- Object[] getArgs(); : 전달되는 모든 파라미터들을 Object 배열로 가져온다.
	  	- String getKind(); : 해당 Advice 타입을 알아낸다.
	  	- Signature getSignature(); : 실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용
	  	- Object getTarget(); : tatget 객체를 알아낼때 사용
	  	- Object getThis(); : Advice를 행하는 객체를 알아낼 때 사용
	  	 
	  
	 */
	
	private String getMethodName(Signature signature) {
		String methodName = signature.toString();
		String[] nameArr = methodName.split("\\.");
		methodName =  nameArr[nameArr.length-2] + "." + nameArr[nameArr.length-1];
		return methodName;
	}
	
	private String getTargetType(String target) {
		String name = "";
		if(target.contains("Controller")) {
			name = "Controller";
		} else if (target.contains("Biz")) {  //Service
			name = "Biz";
		} else if (target.contains("Dao")) {  // DAO
			name = "dao";
		}	
		return name;
	}
	

	
	
}
