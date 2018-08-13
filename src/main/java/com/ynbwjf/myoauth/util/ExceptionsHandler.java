package com.ynbwjf.myoauth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
	protected static Logger LOG = LoggerFactory.getLogger(ExceptionsHandler.class);

	public ExceptionsHandler() {
		LOG.info(this.getClass().getName() + " Init");
	}
	@ExceptionHandler(Exception.class) // 可以直接写@EceptionHandler，IOExeption继承于Exception
	public void allExceptionHandler(Exception exception) {
		LOG.error("错误:",exception);
	}
}
