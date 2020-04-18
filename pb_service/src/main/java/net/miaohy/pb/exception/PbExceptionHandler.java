package net.miaohy.pb.exception;

import lombok.extern.slf4j.Slf4j;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.model.ResultCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * 
 * @author xujinma
 */
@Slf4j
@RestControllerAdvice
public class PbExceptionHandler {
	/**
	 * 自定义异常
	 */
	@ExceptionHandler(PbException.class)
	public Result handleRRException(PbException e){
		log.error(e.getMessage(), e);
		return Result.fail(ResultCode.BUSINESS_EXCEPTION);
	}

	/**
	 * 方法参数校验异常
	 * @param e
	 * @return
	 */
//	@ExceptionHandler(ConstraintViolationException.class)
//	public Result handleConstraintViolationException(ConstraintViolationException  e){
//		log.error(e.getMessage(), e);
//		return Result.fail(ResultCode.FAIL_Valid.getCode(),e.getMessage());
//	}

	/**
	 * Bean 校验异常
	 * hibernate validation   @NotNull @Min等
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handleNotValidExceptionHandler(MethodArgumentNotValidException e){
		String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
		log.error(errorMessage, e);
		return Result.fail(ResultCode.PARAMETER_EXCEPTION);
	}

	/**
	 * Exception异常
	 */
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e){
		log.error(e.getMessage(), e);
		return Result.fail(ResultCode.SPRING_BOOT_PLUS_EXCEPTION);
	}
}
