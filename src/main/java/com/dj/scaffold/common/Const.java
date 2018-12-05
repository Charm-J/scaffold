package com.dj.scaffold.common;

/**
 * 常量类
 */
public class Const {
    public static final String MSG_OK = "OK";
    public static final String TOKEN = "token";
    public static final String UTF_8 = "UTF-8";

    public static final String T0 = "T0";
    public static final Integer EXPIRED = 30*60;

    // 分页
    public static final Integer DEFAULT_PAGENUM = 1;
    public static final Integer DEFAULT_PAGESIZE = 5;


    // redis缓存键
    public class RedisKeys {
        /**
         * 命名空间
         */
        private static final String NAMESPACE = "myApi:";

        /**
         * 管理平台-系统用户
         */
        public static final String SYS_USER = NAMESPACE + "sys_user";
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
