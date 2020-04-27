package com.semi.update.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// #19 interceptor 
public class LoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	// 컨트롤러가 핸들러 매핑되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("[Interceptor] preHandle \n");
		// spring 3.2 이상부터는 servlet-context.xml에서
		// <exclude-mapping-path>태그를 통해 설정할 수 있다

		return true; // DispatcherServlet >> preHandle()가 return false; @controller로
		// 가는것을 막는다.
	}

	// controller가 rendering(서버에서 jsp 컴파일 되기전에)되기전에 >> view로 변환되기 전을 의미
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// logger.info("[Interceptor] postHandle");
	}

	// view rendering된 후 (서버에서 문서화 된 후)
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// logger.info("[Interceptor] afterCompletion");
	}

	
}
