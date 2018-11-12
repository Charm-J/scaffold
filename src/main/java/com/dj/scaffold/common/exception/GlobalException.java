package com.dj.scaffold.common.exception;

import com.dj.scaffold.common.Result;
import com.dj.scaffold.common.ResultHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;


/**
 * 统一异常捕获类
 */
@RestControllerAdvice
public class GlobalException {
    private final static Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errHandler(HttpServletRequest req, Exception e) {
        String url = req.getRequestURL().toString();
        if (e instanceof AppException) {
            AppException ae = (AppException) e;
            logger.error("+++++++++++++>>>>>{}: {}", url, ae.getMessage());
            return ResultHelper.error(ae.getCode(), ae.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            logger.error("+++++++++++++>>>>>{}: {}", url, e.getMessage());
            return ResultHelper.error(ExceptionEnum.WRONG_REQ);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            logger.error("+++++++++++++>>>>>{}: {}", url, e.getMessage());
            return ResultHelper.error(ExceptionEnum.ERR_REQ_TYPE);
        } else if (e instanceof MultipartException) {
            logger.error("+++++++++++++>>>>>{}: {}", url, e.getMessage());
            return ResultHelper.error(ExceptionEnum.NOT_A_MULTIPART_REQUEST);
        } else if (e instanceof IllegalArgumentException) {
            logger.error("+++++++++++++>>>>>{}: {}", url, e.getMessage());
            return ResultHelper.error(ExceptionEnum.PARAM_INVALID.getCode(), e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            logger.error("+++++++++++++>>>>>{}: {}", url, e.getMessage());
            return ResultHelper.error(ExceptionEnum.PARAM_INVALID.getCode(), e.getMessage());
        }
        logger.error("+++++++++++++>>>>>{}: ", url, e);
        return ResultHelper.error(ExceptionEnum.INTERNAL_ERROR);
    }
}
