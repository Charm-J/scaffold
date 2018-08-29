package com.dj.scaffold.util;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class ParamUtil {

    public static Boolean isEmpty(Long param){
        if(null != param && param != 0L){
            return false;
        }
        return true;
    }
}
