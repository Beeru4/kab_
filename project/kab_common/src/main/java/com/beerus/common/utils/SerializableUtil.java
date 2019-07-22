package com.beerus.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.ModelMap;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * @Author Beerus
 * @Description 序列化工具类
 * @Date 2019-06-19
 **/
public class SerializableUtil {
    /**
     * JSON 转对象
     *
     * @param stg   JSON字符串
     * @param clazz 类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String stg, Class<T> clazz) {
        return JSON.parseObject(stg, clazz);
    }

    /**
     * 把LinkHashMap转换为Pager
     *
     * @param modelMap consumer传过来的数据
     * @param clazz    要转的类型
     * @param <T>
     * @return
     */
    public static <T> Pager<T> parsePager(ModelMap modelMap, Class<T> clazz) {
        //通过该对象转化为Bean
        ObjectMapper mapper = new ObjectMapper();
        //转换为分页对象
        Pager<T> pager = mapper.convertValue(modelMap.get("data"), Pager.class);
        // 转换查询的数据并重新复制数据
        pager.setDatas(mapper.convertValue(pager.getDatas(), new TypeReference<List<T>>() {
        }));
        return pager;
    }

}
