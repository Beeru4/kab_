package com.beerus.common.base;

import org.springframework.ui.ModelMap;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author Beerus
 * @Description 基类控制器
 * @Date 2019-06-19
 **/
public class BaseController {

    private final int ERROR_CODE = 500;
    private final String ERROR_DATA = "error";
    private final String ERROR_MSG = "provider is close or provider is error";

    /**
     * 返回模型数据
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @return
     */
    public ModelMap getModelMap(int code, Object data, String msg) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("code", code);
        modelMap.put("data", data);
        modelMap.put("msg", msg);
        return modelMap;

    }

    /**
     * 返回错误模型数据
     *
     * @return
     */
    public ModelMap getErrorModelMap() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("code", ERROR_CODE);
        modelMap.put("data", ERROR_DATA);
        modelMap.put("msg", ERROR_MSG);
        return modelMap;

    }

    /**
     * 得到key值
     *
     * @param session 使用会话拿到ID
     * @return
     */
    public String getKey(HttpSession session) {
        return session.getId().replace("-", "");
    }

    /**
     * 生成uuid
     *
     * @return
     */
    public String getUUid() {
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        return uuid;
    }




}
