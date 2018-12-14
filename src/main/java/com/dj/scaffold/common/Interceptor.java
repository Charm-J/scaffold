package com.dj.scaffold.common;

import com.dj.scaffold.common.annotation.Auth;
import com.dj.scaffold.common.exception.AppException;
import com.dj.scaffold.common.exception.ExceptionEnum;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Interceptor implements HandlerInterceptor {

    @Autowired
    private Redis redis;

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    private static final String ORIGIN = "origin";
    private static final String TRUE = "true";
    private static final String METHODS = "GET, POST, PUT, DELETE, OPTIONS";
    private static final String HEADERS = "X-Requested-With,Origin, Content-Type, Cookie, Accept";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 允许跨域
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(ORIGIN));
        response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, TRUE);
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, METHODS);
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, HEADERS);
        // 权限校验
        if (handler instanceof HandlerMethod) {
            if (null != ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(Auth.class)) {
                // 类验证
                validateToken(request);
            } else if (((HandlerMethod) handler).hasMethodAnnotation(Auth.class)) {
                // 方法验证
                validateToken(request);
            }
        }
        return true;
    }

    /**
     * token校验
     */
    private void validateToken(HttpServletRequest request) {
        String token = request.getHeader(Const.TOKEN);
        if (Strings.isNullOrEmpty(token)) {
            token = request.getParameter(Const.TOKEN);
            if (Strings.isNullOrEmpty(token)) {
                throw new AppException(ExceptionEnum.UNAUTHORIZED);
            }
        }
        String user = redis.get(token);
        if (null == user) {
            throw new AppException(ExceptionEnum.UNAUTHORIZED);
        }
        request.setAttribute(Const.CURRENT_TOKEN, token);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
