package com.dj.scaffold.util;

import com.dj.scaffold.common.Const;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    public static String encode(String plainText) {
        if (null == plainText || 0 == plainText.trim().length()) {
            return null;
        }
        String encoded = null;
        try {
            byte[] bytes = plainText.getBytes(Const.UTF_8);
            encoded = Base64.getEncoder().encodeToString(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    public static String decode(String plainText) {
        if (null == plainText || 0 == plainText.trim().length()) {
            return null;
        }
        try {
            byte[] bytes = plainText.getBytes(Const.UTF_8);
            byte[] decoded = Base64.getDecoder().decode(bytes);
            return new String(decoded, Const.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}
