package com.beerus.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author Beerus
 * @Description 铭感词工具类
 * @Date 2019-07-03
 **/
@Component
@RefreshScope
public class SensitiveWord {
    @Value("${censorWords}")
    private List<String> arrayList; // 铭感词库(使用Config初始化敏感词库)
    private Logger logger = LoggerFactory.getLogger(SensitiveWord.class);
    /**
     * @param str 将要被过滤信息
     * @return 过滤后的信息
     */
    public boolean filterInfo(String str) {
        StringBuilder buffer = new StringBuilder(str);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
        String temp;
        for (int x = 0; x < arrayList.size(); x++) {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1; ) {
                findIndexSize = start + temp.length();// 从已找到的后面开始找
                Integer mapStart = hash.get(start);// 起始位置
                if (mapStart == null || (mapStart != null && findIndexSize > mapStart)) {// 满足1个，即可更新map
                    logger.info("exists sensor");
                    // 找到敏感词
                    return true;
                }
            }
        }
        // 未找到
        return false;
    }


}

