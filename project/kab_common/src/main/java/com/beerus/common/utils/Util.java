package com.beerus.common.utils;



/**
 * @Author Beerus
 * @Description
 * @Date 2019-06-17
 **/
public class Util {
    /**
     * 判断是否为空
     *
     * @return false != 空 true == 空
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof Integer) {
            return !((Integer) obj > 0);
        } else if ("".equals(obj.toString().trim())) {
            return true;
        }
        return false;
    }


}
