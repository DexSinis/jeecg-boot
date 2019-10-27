package org.jeecg.exception;

import org.jeecg.util.response.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
@Slf4j
public class InterfaceZxExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(InterfaceZxException.class)
	public ServiceResult<?> handleRRException(InterfaceZxException e){
		log.error(e.getMessage(), e);
		return ServiceResult.buildError(e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ServiceResult<?> handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return ServiceResult.buildError(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ServiceResult<?> handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return ServiceResult.buildError("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public ServiceResult<?> handleAuthorizationException(AuthorizationException e){
		log.error(e.getMessage(), e);
		return ServiceResult.buildError("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public ServiceResult<?> handleException(Exception e){
		log.error(e.getMessage(), e);
		return ServiceResult.buildError(e.getMessage());
	}
}
