package com.feng.framework.exception;


import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.model.response.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName GlobalExceptionHandler
 * @Author 小风谷
 * @Date 2021/3/1 21:58
 * @Version 1.0
 * @Description
 */
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = BusinessException.class)
    public AjaxResult bizExceptionHandler(BusinessException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.defineError(e);
    }

    /**
     *处理其他异常
     *
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResult exceptionHandler( Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.otherError(ErrorEnum.INTERNAL_SERVER_ERROR);

    }
}
