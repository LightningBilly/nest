package com.yiyjm.nest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理程序的页面
 *
 * @author jonny
 * @date 2020/04/30
 */
@ControllerAdvice
public class ExceptionHandlerForPage {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerForPage.class);

	/**
	 * 解决异常
	 *
	 * @param e e
	 * @return {@link JsonResult<String>}
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public JsonResult<String> resolveException(Exception e) {
		logger.warn("ExceptionMessage：" + e.getMessage());
		logger.warn("ExceptionCause：" + e.getCause());
		JsonResult<String> result = new JsonResult<>();
		return result.error(-1, "服务器Page遇到错误，请查看日志");
	}

}
