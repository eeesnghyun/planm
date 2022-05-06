package com.app.planm.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Log logger = LogFactory.getLog(this.getClass());
	private final static String DEV_SERVER = "localhost, 127.0.0.1";
	
	/**
	 * 예외 내용 로그 출력
	 * @param request
	 * @param e
	 */
	protected void showException(HttpServletRequest request, Exception e) {
		if (DEV_SERVER.contains(request.getServerName())) {
			e.printStackTrace();
		} else {							
			logger.debug(":: Exception cause/content ::");
			logger.debug(e.toString());
		}
	}	
	
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<MsgEntity> globalCustomException(HttpServletRequest request, CustomException e) {
		showException(request, e);
		
		return ResponseEntity.badRequest()
				.body(new MsgEntity(500, e.getMessage()));
	}
	
	/**
	 * 개발자가 직접 핸들링하지 않는 모든 예외 처리
	 * @param e
	 * @return status(500), message
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<MsgEntity> globalException(HttpServletRequest request, Exception e) {
		String message = e.getMessage();
		
		showException(request, e);
				
		return ResponseEntity.internalServerError()
				.body(new MsgEntity(500, message));
	}
	
}
