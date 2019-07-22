package com.beerus.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Author Beerus
 * @Description 返回结果
 * @Date 2019-06-17
 **/
public class Result {
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 本类的JSON字符串
     */
    private String json;


    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
        //设置该类字符串
        json = JSON.toJSONString(this);
    }

    /**
     * 返回JSON数据
     *
     * @return
     */
    public String getJson() {
        return json;
    }
}
