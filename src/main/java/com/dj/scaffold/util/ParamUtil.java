package com.dj.scaffold.util;

import com.dj.scaffold.common.exception.AppException;
import com.dj.scaffold.common.exception.ExceptionEnum;
import org.springframework.validation.BindingResult;

public class ParamUtil {

    public static Boolean isEmpty(Long param) {
        if (null != param && param != 0L) {
            return false;
        }
        return true;
    }

    public static void valid(BindingResult result) throws AppException {
        if (result.hasErrors()) {
            throw new AppException(ExceptionEnum.PARAM_INVALID.getMsg() + ":" + result.getFieldError().getDefaultMessage(), ExceptionEnum.PARAM_INVALID.getCode());
        }
    }
}
