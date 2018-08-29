package com.dj.scaffold.common;

/**
 * 常量类
 */
public class Const {
    public static final String MSG_OK = "OK";
    public static final String TOKEN = "token";
    public static final String UTF_8 = "UTF-8";
    public static final String ISO_8859_1 = "ISO-8859-1";

    public static final String T0 = "T0";
    public static final String ONE = "1";

    // 分页
    public static final Integer DEFAULT_PAGENUM = 1;
    public static final Integer DEFAULT_PAGESIZE = 5;


    // redis缓存键
    public class RedisKeys {
        public static final String USER_INFO = "user_info";
    }

    // 用户类型
    public enum UserType {
        NORMAL(0),  //普通用户
        VIP(1);     //VIP用户
        public int v;

        UserType(int v) {
            this.v = v;
        }

        public int getV() {
            return v;
        }
    }

}
