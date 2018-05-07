package com.thon.controller.rest;

import com.google.common.collect.Maps;
import com.thon.commons.beanvalidator.BeanValidators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public final ResponseEntity<?> handleException(ConstraintViolationException ex, HttpServletRequest request) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("request", request.getRequestURI());
		map.put("error_code", "");
		map.put("error", BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations()));

		
		return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
	}
}
