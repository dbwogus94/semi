package com.semi.update.All.exception;

/*
 	dao에서 발생하는 모든 예외를 처리하기 위한 클래스
 	- 해당 클래스는 매번 해당 예외에 대한 결과를 controller에서 매번 처리해야하는 불편함이 있다.
 	>>>
 	 ex) 매번 아래와 같이 에러 헨들러를 만들어야함
 	 @ExceptionHandler(GlobalException.class)
 	 public String 메서드이름(){해당 에러처리  return ModelendVeiw}
 	 
 	
 */
public class GlobalException extends RuntimeException{
	private static final long serialVersionUID = 1L;	// 직열화 객체마다 다른 번호를 붙여야함
	
	// 기본생성자
	public GlobalException() {
		super("GlobalException default mag");
	}
	
	// 부모인 RuntimeException에게 에러메세지를 전달하는 생성자
	public GlobalException(String errorMessage) {
		super(errorMessage);
	}

}
