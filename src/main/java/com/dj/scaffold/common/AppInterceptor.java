package com.dj.scaffold.common;

import com.alibaba.fastjson.JSONObject;
import com.dj.scaffold.common.annotation.Auth;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AppInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(AppInterceptor.class);

    @Autowired
    private Redis redis;

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    private static final String ORIGIN = "origin";
    private static final String TRUE = "true";
    private static final String GET = "GET";
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
            final String method = request.getMethod();
            final StringBuffer requestURL = request.getRequestURL();
            String param = "";
            if (GET.equalsIgnoreCase(method) && StringUtils.isNotBlank(param)) {
                param = "?" + request.getQueryString();
            }
            logger.info(">>>>>>>>>>>>>>>>>>request url({}): {}{}", method, requestURL, param);

            if (null != ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(Auth.class)) {// 类级别
                validateToken(request);
            } else if (((HandlerMethod) handler).hasMethodAnnotation(Auth.class)) { // 方法级别
                validateToken(request);
            }

            request.setAttribute(Const.T0, System.currentTimeMillis());
        }

        return true;
    }

    /**
     * 校验token
     *
     * @param request
     */
    private void validateToken(HttpServletRequest request) {
        String token = request.getHeader(Const.TOKEN);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<{}, cost: {}s",
                request.getRequestURL(),
                (System.currentTimeMillis() - (Long) request.getAttribute(Const.T0)) / 1000.0
        );
    }
}
