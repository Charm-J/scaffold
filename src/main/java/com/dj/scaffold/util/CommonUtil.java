package com.dj.scaffold.util;

import java.util.Random;


/**
 * 公共工具类
 */
public class CommonUtil {

    /**
     * 获取32位随机字符串
     *
     * @return
     */
    public static String gen() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取6位随机数字
     *
     * @return
     */
    public static String getSix() {
        Random rad = new Random();
        String result = rad.nextInt(1_000_000) + "";
        if (result.length() != 6) {
            return getSix();
        }
        return result;
    }

    /**
     * 生成随机主键-9位
     */
    public static Long genarateId() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            str.append(random.nextInt(10));
        }
        return Long.valueOf(str.toString());
    }
}
